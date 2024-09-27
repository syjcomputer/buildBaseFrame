package com.example.buildbaseframe.infrastructure.user.persistence.repository;

import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.UserPo;
import org.apache.ibatis.annotations.Param;

/**
 * <b>用户基础设施层持久化接口</b>
 *
 * @author lq
 * @version 1.0
 */
public interface UserRepository {
    public UserPo get(Long userId);

    public int update(UserPo po, Long userId);

    public UserPo findByName(String name);

    public Long insertOneUser(UserPo userPo);
}
