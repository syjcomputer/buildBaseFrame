package com.example.buildbaseframe.application.user.service.impl;

import com.example.buildbaseframe.application.user.converter.PwdAppConverter;
import com.example.buildbaseframe.application.user.converter.UserAppConverter;
import com.example.buildbaseframe.application.user.dto.PwdLoginDto;
import com.example.buildbaseframe.application.user.dto.UserInfoDto;
import com.example.buildbaseframe.application.user.service.PwdLoginService;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.PwdLoginRepository;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.UserRepository;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.PwdLoginPo;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.UserPo;
import com.example.buildbaseframe.utils.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/4/6 22:02
 * @version: 1.0
 */
@Service
public class PwdLoginServiceImpl implements PwdLoginService {

    @Autowired
    PwdLoginRepository pwdLoginRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PwdAppConverter pwdAppConverter;

    @Autowired
    UserAppConverter userAppConverter;

    /**
     * 验证密码登录时是否密码正确
     * @param user
     * @param pwd
     * @return
     * @throws NotFoundException
     */
    @Override
    public boolean verifyPwdLogin(UserInfoDto user, String pwd){
//        UserPo userPo = userRepository.findByName(nickName);

        UserPo userPo = userAppConverter.toUserPo(user);

        if(userPo.getId() == null){
            throw new NotFoundException("用户不存在");
        }

        PwdLoginPo pwdLoginPo = pwdLoginRepository.findByUser(userPo.getId());

        if(Objects.equals(pwdLoginPo.getPassword(), pwd)==false){
            return false;
            //
        }
//        return pwdAppConverter.toPwdLoginDto(pwdLoginPo);
        return true;
    }

    /**
     * 添加新的密码验证
     * @param pwdLoginDto
     */
    @Override
    public void insertOneLoginPwd(PwdLoginDto pwdLoginDto){
        PwdLoginPo pwdLoginPo = pwdAppConverter.toPwdLoginPo(pwdLoginDto);
//        pwdLoginPo.setCreateTime(LocalDateTime.now());
//        pwdLoginPo.setUpdateTime(LocalDateTime.now());

        pwdLoginRepository.insertOnePwdLogin(pwdLoginPo);
    }
}
