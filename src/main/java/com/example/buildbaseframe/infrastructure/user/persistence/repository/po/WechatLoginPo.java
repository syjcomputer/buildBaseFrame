package com.example.buildbaseframe.infrastructure.user.persistence.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.buildbaseframe.infrastructure.common.pojo.BaseDatabaseLogicDeletePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 微信登录po
 *
 * @author LJTTT
 * @version 1.0
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_wechat_login")
public class WechatLoginPo extends BaseDatabaseLogicDeletePo {
    private static final long serialVersionUID = 1L;

    /**
     * open id
     */
    @TableField
    private String openId;

    /**
     * 用户id
     */
    @TableField
    private Long userId;

}
