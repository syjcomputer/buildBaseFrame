package com.example.buildbaseframe.infrastructure.user.persistence.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.buildbaseframe.infrastructure.common.pojo.BaseDatabaseLogicDeletePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <b>UserPo</b>
 * <p>
 *     用户 PO
 * </p>
 *
 * @author lq
 * @version 1.0
 */
@ApiModel(description = "用户信息")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_user")
public class UserPo extends BaseDatabaseLogicDeletePo {
    private static final long serialVersionUID = 1L;

    /**
     * 用户昵称
     */
    @TableField(value = "name")
    @ApiModelProperty("用户名")
    private String nickname;

    /**
     * 用户头像url
     */
    @TableField(value = "avatar_url")
    @ApiModelProperty("头像")
    private String avatarUrl;

    /**
     * 用户个人介绍
     */
    @TableField(value = "description")
    @ApiModelProperty("个人介绍")
    private String introduction;

    /**
     * 用户性别
     */
    @TableField(value = "gender")
    @ApiModelProperty("性别")
    private Integer gender;

}
