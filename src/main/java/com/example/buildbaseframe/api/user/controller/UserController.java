package com.example.buildbaseframe.api.user.controller;

//import com.example.buildbaseframe.api.common.vo.TwoVo;
import com.alibaba.fastjson.JSON;
import com.example.buildbaseframe.api.user.converter.GenderEnumConverter;
import com.example.buildbaseframe.api.user.converter.PwdLoginApiConverter;
import com.example.buildbaseframe.api.user.vo.PwdLoginVo;
import com.example.buildbaseframe.api.user.vo.RegisterVo;
import com.example.buildbaseframe.application.user.dto.WechatLoginDto;
import com.example.buildbaseframe.application.user.enums.GenderEnum;
import com.example.buildbaseframe.application.user.service.WechatLoginService;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.PwdLoginRepository;
import com.example.buildbaseframe.utils.exception.JwtException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.buildbaseframe.api.common.auth.SecurityContextHolder;
import com.example.buildbaseframe.api.common.respond.CommonResult;
import com.example.buildbaseframe.api.user.converter.UserApiConverter;
import com.example.buildbaseframe.api.user.req.UserCreateReq;
import com.example.buildbaseframe.api.user.vo.UserInfoVo;
import com.example.buildbaseframe.application.user.dto.PwdLoginDto;
import com.example.buildbaseframe.application.user.dto.UserInfoDto;
import com.example.buildbaseframe.application.user.service.PwdLoginService;
import com.example.buildbaseframe.application.user.service.UserService;
import com.example.buildbaseframe.utils.auth.JwtGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * <b>用户管理Controller</b>
 *
 * @author lq
 * @version 1.0
 */
@Validated
@Api(tags={"用户接口"})
@RestController // 既需要返回html数据又需要返回非html数据，类上@controller，方法上@requestcontroller或@responsebody
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private PwdLoginService pwdLoginService;

    @Autowired
    private UserApiConverter userApiConverter;

    @Autowired
    private PwdLoginApiConverter pwdLoginApiConverter;

    @Autowired
    private GenderEnumConverter genderEnumConverter;

    @Autowired
    private JwtGenerator jwtGenerator;

    /**
     * 用户获得自己的基本信息
     *
     * @param
     * @return
     * @methodName getOwnInfo
     */
    @ApiOperation(value = "获得用户信息", notes = "获取用户基本信息")
    // 可以不用返回CommonResullt，有包装器会拦截
    @RequestMapping(value = "/info/own", method = RequestMethod.GET)
    public UserInfoVo getOwnInfo() {
        return userApiConverter.toUserInfoVo(service.getUserInfo(SecurityContextHolder.getUserId()));
    }

    /**
     * 根据id获得用户信息
     *
     * @param
     * @return
     * @methodName getUserInfo
     */
    @RequestMapping(value = "/info/{userId}", method = RequestMethod.GET)
    public UserInfoVo getUserInfo(@PathVariable("userId") String userId) {


        return userApiConverter.toUserInfoVo(service.getUserInfo(Long.valueOf(userId)));
    }

    /**
     * 用户修改自己的基本信息
     *
     * @param
     * @return
     * @methodName putUserInfo
     */
    @RequestMapping(value = "/info/own", method = RequestMethod.PUT)
    public UserInfoVo putUserInfo(@RequestBody UserCreateReq req) {
        UserInfoDto dto = userApiConverter.toUserInfoDto(req);
        return userApiConverter.toUserInfoVo(service.updateUserInfo(dto, SecurityContextHolder.getUserId()));
    }

    /**
     * 密码验证登录
     * @param json
     * @param response
     * @return
     * @throws NotFoundException
     */
    @ApiOperation(value = "密码登录")
    @RequestMapping(value = "login/pwd", method = RequestMethod.POST)
    @SneakyThrows(JsonProcessingException.class)
    public CommonResult login(@RequestBody String json,
                              HttpServletResponse response) throws NotFoundException{

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        String nickname = rootNode.get("nickname").asText();
        String password = rootNode.get("password").asText();

        UserInfoDto user = service.getUserByname(nickname);

        if(user==null){
            return CommonResult.failure("账号或密码错误");
        }
        UserInfoVo userInfoVo = userApiConverter.toUserInfoVo(user);

        boolean flag = pwdLoginService.verifyPwdLogin(user, password);

        String jwt = jwtGenerator.createJwt(user.getId());

        response.addHeader("Authorization", jwt);

        if(flag){
            return CommonResult.success(userInfoVo);
        }else {
            return CommonResult.failure("账号或密码错误");

        }

    }

    /**
     * 用户注册
     * @param json
     * @return
     */
    @ApiOperation(value = "注册")
    @SneakyThrows(JsonProcessingException.class)
    @RequestMapping(value = "register/pwd", method = RequestMethod.POST)
//    public CommonResult register(@RequestBody String json){ 不需要CommonResult，包装器会自动包装
    public RegisterVo register(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        String email = jsonNode.get("email").asText();
        String password = jsonNode.get("password").asText();
        String nickname = jsonNode.get("nickname").asText();
        String avatarUrl = jsonNode.get("avatarUrl").asText();
        String description = jsonNode.get("introduction").asText();
        String gender = jsonNode.get("gender").asText();

        GenderEnum gender2 = genderEnumConverter.toGenderEnum(gender);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setNickname(nickname);
        userInfoDto.setGender(gender2);
        userInfoDto.setIntroduction(description);
        userInfoDto.setAvatarUrl(avatarUrl);
        Long userId = service.insertOneUser(userInfoDto);
        userInfoDto.setId(userId);
        UserInfoVo userInfoVo = userApiConverter.toUserInfoVo(userInfoDto);

        PwdLoginDto pwdLoginDto = new PwdLoginDto();
        pwdLoginDto.setEmail(email);
        pwdLoginDto.setPassword(password);
        pwdLoginDto.setUserId(userId);
        pwdLoginService.insertOneLoginPwd(pwdLoginDto);
        PwdLoginVo pwdLoginVo = pwdLoginApiConverter.toPwdLoginVo(pwdLoginDto);

        RegisterVo registerVo = new RegisterVo();

        BeanUtils.copyProperties(pwdLoginVo, registerVo);
        BeanUtils.copyProperties(userInfoVo, registerVo);

        return registerVo;
//        return CommonResult.success(registerVo);
    }

}
