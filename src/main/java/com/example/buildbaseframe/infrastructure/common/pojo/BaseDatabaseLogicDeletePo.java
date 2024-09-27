package com.example.buildbaseframe.infrastructure.common.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <b>Base 数据库逻辑删除 PO</b>
 *
 * @author lq
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"dataStatus"})
public class BaseDatabaseLogicDeletePo extends BaseDatabasePo {

    /**
     * 逻辑删除标志字段，可以控制是sql删除还是sql修改
     */
    @TableLogic(value = "0", delval = "1")
    @TableField(fill = FieldFill.INSERT)
    protected Long dataStatus;

}
