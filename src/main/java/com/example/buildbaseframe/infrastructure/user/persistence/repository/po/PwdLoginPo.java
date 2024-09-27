package com.example.buildbaseframe.infrastructure.user.persistence.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.buildbaseframe.infrastructure.common.pojo.BaseDatabaseLogicDeletePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 密码登录po
 *
 * @author LJTTT
 * @version 1.0
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_pwd_login")
public class PwdLoginPo extends BaseDatabaseLogicDeletePo {
    private static final long serialVersionUID = 1L;

    /**
     * 邮箱
     */
    @TableField
    private String email;

    /**
     * 密码
     */
    @TableField
    private String password;

    /**
     * 用户id
     */
    @TableField
    private Long userId;
}
