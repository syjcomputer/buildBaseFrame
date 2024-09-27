package com.example.buildbaseframe.infrastructure.user.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.buildbaseframe.infrastructure.user.persistence.mysql.mapper.PwdLoginPoMapper;
import com.example.buildbaseframe.infrastructure.user.persistence.mysql.mapper.UserPoMapper;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.PwdLoginRepository;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.PwdLoginPo;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: sunyujie
 * @time: 2023/4/6 16:50
 * @version: 1.0
 */
@Repository
public class PwdLoginRepositoryImpl implements PwdLoginRepository {

    @Autowired
    PwdLoginPoMapper pwdLoginPoMapper;

    @Autowired
    UserPoMapper userPoMapper;

    @Override
    public PwdLoginPo findByUser(Long userId){
//        UserPo userPo = userPoMapper.findByName(nickname);
//        QueryWrapper<UserPo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", userId);

        PwdLoginPo pwdLoginPo = pwdLoginPoMapper.findByUser(userId);

        return pwdLoginPo;
    }

    @Override
    public void insertOnePwdLogin(PwdLoginPo pwdLoginPo){
        pwdLoginPoMapper.insert(pwdLoginPo);
    }
}
