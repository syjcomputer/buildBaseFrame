package com.example.buildbaseframe.infrastructure.user.persistence.repository;


import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.PwdLoginPo;
import org.apache.ibatis.annotations.Param;

/**
 * 密码登录基础设施层持久化接口
 *
 * @author LJTTT
 * @version 1.0
 *
 */
public interface PwdLoginRepository {

    public PwdLoginPo findByUser(Long userId);

    public void insertOnePwdLogin(PwdLoginPo pwdLoginPo);

}
