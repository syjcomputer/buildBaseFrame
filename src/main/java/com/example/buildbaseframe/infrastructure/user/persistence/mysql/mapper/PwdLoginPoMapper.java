package com.example.buildbaseframe.infrastructure.user.persistence.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.PwdLoginPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName: PwdLoginPoMapper
 * @Description:
 * @Author: syj
 * @Date: 2023/4/6
 * @Version: 1.0
 */
@Mapper
public interface PwdLoginPoMapper extends BaseMapper<PwdLoginPo> {

    public PwdLoginPo findByUser(@Param("userId") Long userId);
}
