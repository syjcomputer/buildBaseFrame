package com.example.buildbaseframe.infrastructure.user.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.buildbaseframe.infrastructure.user.persistence.mysql.mapper.UserPoMapper;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.UserRepository;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <b>用户基础设施持久化实现类</b>
 *
 * @author lq
 * @version 1.0
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserPoMapper mapper;


    @Override
    public UserPo get(Long userId) {
        return mapper.selectById(userId);
    }

    @Override
    public int update(UserPo po, Long userId) {
        UpdateWrapper<UserPo> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", userId);
        if (po.getNickname() != null)
            wrapper.set("nickname", po.getNickname());
        if (po.getAvatarUrl() != null)
            wrapper.set("avatar_url", po.getAvatarUrl());
        if (po.getIntroduction() != null)
            wrapper.set("introduction", po.getIntroduction());
        if (po.getGender() != null)
            wrapper.set("gender", po.getGender());
        return mapper.update(po, wrapper);
    }

    @Override
    public UserPo findByName(String name){
        return mapper.findByName(name);
    }

    @Override
    public Long insertOneUser(UserPo userPo){
        mapper.insert(userPo);
        return userPo.getId();
    }
}
