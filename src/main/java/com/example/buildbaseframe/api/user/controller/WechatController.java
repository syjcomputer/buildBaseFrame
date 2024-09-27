package com.example.buildbaseframe.api.user.controller;

import com.alibaba.fastjson.JSON;
import com.example.buildbaseframe.api.common.respond.CommonResult;
import com.example.buildbaseframe.api.user.converter.GenderEnumConverter;
import com.example.buildbaseframe.api.user.converter.PwdLoginApiConverter;
import com.example.buildbaseframe.api.user.converter.UserApiConverter;
import com.example.buildbaseframe.api.user.vo.UserInfoVo;
import com.example.buildbaseframe.application.user.dto.UserInfoDto;
import com.example.buildbaseframe.application.user.dto.WechatLoginDto;
import com.example.buildbaseframe.application.user.enums.GenderEnum;
import com.example.buildbaseframe.application.user.service.PwdLoginService;
import com.example.buildbaseframe.application.user.service.UserService;
import com.example.buildbaseframe.application.user.service.WechatLoginService;
import com.example.buildbaseframe.utils.auth.JwtGenerator;
import com.example.buildbaseframe.utils.exception.JwtException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

/**
 * @description:
 * 定义了两种扫码登录方法，1.jsp去redirect扫码；2.login/wxCode控制器方法扫码
 * 需要先去开放平台验证微信信息
 * @author: sunyujie
 * @time: 2023/4/9 16:11
 * @version: 1.0
 */
@Validated
@Slf4j
@Controller
@RequestMapping(value = "/api/v1/user/wechat")
public class WechatController {
    @Value("${wx.open.app_id}")
    private String appId;

    @Value("${wx.open.app_secret}")
    private String appSecret;

    @Value("${wx.open.redirect_url}")
    private String wxRedirectUrl;

    private String tmpToken = "sunyujie2";

    @Autowired
    private RestTemplate restTemplate;  // 调用远程api常用的同步rest模板

    @Autowired
    private UserService service;

    @Autowired
    private WechatLoginService wechatLoginService;

    @Autowired
    private UserApiConverter userApiConverter;

    @Autowired
    private JwtGenerator jwtGenerator;

    /**
     * 生成微信扫描二维码
     * @return 定向到请求微信地址
     */
    @GetMapping("/login/wxCode")
    public String getWxCode() throws UnsupportedEncodingException {

        //微信开放平台授权baseUrl
        // qrconnect是扫码登录，但scope错误因此直接授权登录
//        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
        String baseUrl = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";


        //对redirect_url进行URLEncoder编码
        String redirectUrl = wxRedirectUrl;

        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException();
        }

        //设置%s的值
        String url = String.format(
                baseUrl,
                appId,
                redirectUrl,
                "atguigu"
        );

        //重定向到请求微信地址
        return "redirect:" + url;
    }

    /**
     *  当扫码之后微信会来调用的，/weixinlogin记得跟前端的回调url的最后面一样
     */
    @RequestMapping(value = "/login/wx",name = "进入微信登录方法")
    public CommonResult weixinlogin(String code, String state, HttpServletResponse response) {

        // 查看获取到的code
        log.info("进入微信登入方法，code为：{}, state为：{}",code,state);

        // 根据code获取access_token和openId
        // 微信请求地址标准格式url
        String getAccessTokenAndOpenIdUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ appId +"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";

        // 向微信发送请求获取access_token与openid
        // restTemplate.getForObject：向远程api发送get请求，String.class表示设置response的类型为String
        Map map = JSON.parseObject(restTemplate.getForObject(getAccessTokenAndOpenIdUrl, String.class), Map.class);

        // 开始遍历参数
        map.forEach(
                (key,value)->{
                    log.info("根据code请求微信获取的值，key：{}, value：{}",key,value);
                }
        );

        // 获取accessToken
        String access_token = map.get("access_token").toString();

        // 获取openid
        String openid = map.get("openid").toString();

        // 再次请求微信平台，获取用户信息
        String getUserInfoUrl="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";

        // 发起请求
        Map userInfoMap = JSON.parseObject(restTemplate.getForObject(getUserInfoUrl, String.class), Map.class);
        userInfoMap.forEach((key, value)-> {
            log.info("微信获取用户信息，key为：{}, value为：{}",key,value);
        });

        WechatLoginDto wechatLoginDto = wechatLoginService.findByOpenId(openid);
        String name = (String) userInfoMap.get("nickname");
        UserInfoDto userInfoDto = service.getUserByname(name);

        // 首次登录则注册，否则就直接登录
        if(wechatLoginDto == null){

            wechatLoginDto = new WechatLoginDto();

            if(userInfoDto==null){
                userInfoDto = new UserInfoDto();
                userInfoDto.setNickname((String) userInfoMap.get("nickname"));
                userInfoDto.setAvatarUrl((String) userInfoMap.get("headimgurl"));
                userInfoDto.setIntroduction("");

                // 值为 1 时是男性，值为 2 时是女性，值为 0 时是未知
                int gender = (int) userInfoMap.get("sex");
                switch (gender){
                    case 1:
                        userInfoDto.setGender(GenderEnum.MALE);
                        break;
                    case 2:
                        userInfoDto.setGender(GenderEnum.FEMALE);
                        break;
                    default:
                        userInfoDto.setGender(GenderEnum.UNKNOWN);
                }

                Long userId = service.insertOneUser(userInfoDto);
                userInfoDto.setId(userId);
            }

            wechatLoginDto.setUserId(userInfoDto.getId());
            wechatLoginDto.setOpenId((String) userInfoMap.get("openid"));

            wechatLoginDto = wechatLoginService.insertOneWechatLogin(wechatLoginDto);
        }

        String jwt = jwtGenerator.createJwt(wechatLoginDto.getUserId());
        response.addHeader("Authorization", jwt);

        UserInfoVo userInfoVo = userApiConverter.toUserInfoVo(userInfoDto);

        // 把当前用户信息返回
        return CommonResult.success(userInfoVo);
    }

    /**
     * 验证微信消息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/check")   // 等价于@RequestMapping(value="/check",method=RequestMethod.GET)
    public String checkWxMsg(HttpServletRequest request) {
        log.info("进入微信校验的接口了");
        /**
         * 微信加密签名
         */
        String signature = request.getParameter("signature");
        /**
         * 随机字符串
         */
        String echostr = request.getParameter("echostr");
        /**
         * 时间戳
         */
        String timestamp = request.getParameter("timestamp");
        /**
         * 随机数
         */
        String nonce = request.getParameter("nonce");

        String[] str={timestamp,nonce, tmpToken};
        //将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(str);
        StringBuffer sb = new StringBuffer();
        //将三个参数字符串拼接成一个字符串进行sha1加密
        for (String param:str) {
            sb.append(param);
        }

        //获取到加密过后的字符串
        String encryptStr = DigestUtils.sha1Hex(sb.toString());
//        String encryptStr = EncryptionUtil.encrypt("SHA1", sb.toString());
        //判断加密后的字符串是否与微信的签名一致
        if(signature.equalsIgnoreCase(encryptStr)){
            log.info("签名一致可以通过");
            return echostr;
        }

        log.error("这不是微信发来的消息！！");
        return null;
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView doLogin(){
        ModelAndView modelAndView = new ModelAndView("admin/wechatLogin");

        log.info("前往登入页面");
        return modelAndView; // 跟页面模板名字一样才能跳转
    }
}
