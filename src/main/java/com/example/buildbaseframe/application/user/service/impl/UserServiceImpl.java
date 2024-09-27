package com.example.buildbaseframe.application.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.buildbaseframe.application.common.exception.BusinessException;
import com.example.buildbaseframe.application.common.exception.ExceptionType;
import com.example.buildbaseframe.application.user.converter.UserAppConverter;
import com.example.buildbaseframe.application.user.dto.UserInfoDto;
import com.example.buildbaseframe.application.user.service.UserService;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.UserRepository;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.UserPo;
import com.example.buildbaseframe.utils.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>UserServiceImpl</b>
 *
 * @author lq
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAppConverter userAppConverter;

    /**
     * 根据id获取用户信息
     * @param userId
     * @return
     */
    @Override
    public UserInfoDto getUserInfo(Long userId) {
        UserPo po = userRepository.get(userId);
        if (po == null) {
            throw new NotFoundException("用户信息");
        }

        System.out.println(po);

        return userAppConverter.toUserInfoDto(po);
    }

    /**
     * 根据名字获取用户信息
     * @param name
     * @return
     */
    @Override
    public UserInfoDto getUserByname(String name){
        UserPo userPo = userRepository.findByName(name);

//        System.out.println(userPo);
//        if(userPo==null){
//            throw new NotFoundException("用户不存在");
//        }
        return userAppConverter.toUserInfoDto(userPo);
    }

    /**
     * 更新用户信息
     * @param dto
     * @param userId
     * @return
     * @throws NotFoundException
     */
    @Override
    public UserInfoDto updateUserInfo(UserInfoDto dto, Long userId){
        UserPo po = userAppConverter.toUserPo(dto);
        if (userRepository.get(userId) == null) {
            throw new NotFoundException("用户信息");
        }
        int succ = userRepository.update(po, userId);
//        if (succ == 0) {
//            throw new UpdateFailedBusinessException(UpdateFailedExceptionType.PARAM_VALIDATE_FAILED, "用户信息");
//        }
        return userAppConverter.toUserInfoDto(userRepository.get(userId));
    }

    /**
     * 添加新用户
     * @param userInfoDto
     * @return
     */
    @Override
    public Long insertOneUser(UserInfoDto userInfoDto){
        UserPo userPo = userAppConverter.toUserPo(userInfoDto);
//        userPo.setCreateTime(LocalDateTime.now());
//        userPo.setUpdateTime(LocalDateTime.now());

        UserPo userPo1 = userRepository.findByName(userPo.getNickname());

        if(userPo1!=null){
            throw new BusinessException(ExceptionType.DUPLICATE_ERROR);
        }

        Long id = userRepository.insertOneUser(userPo);

        return id;
    }

//    @Override
//    public Page<FollowDto> getFollowPageToMe(Long userId, Long current, Long pageSize) {
//        List<FollowDto> records = new ArrayList<>();
//        for (FollowPo po : followRepository.getPageToMe(userId, current, pageSize).getRecords()) {
//            records.add(followAppConverter.toFollowDto(po));
//        }
//        Page<FollowDto> page = new Page<>(current, pageSize);
//        page.setRecords(records);
//        return page;
//    }

}
