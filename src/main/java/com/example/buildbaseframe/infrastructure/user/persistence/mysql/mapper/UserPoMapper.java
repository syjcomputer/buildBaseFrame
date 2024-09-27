package com.example.buildbaseframe.infrastructure.user.persistence.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buildbaseframe.infrastructure.common.config.MybatisRedisCache;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.UserPo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <b>UserPoMapper</b>
 *
 * @author lq
 * @version 1.0
 */
// mybatis-plus中BaseMapper自带CRUD
@Mapper
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)   // 开启二级缓存
public interface UserPoMapper extends BaseMapper<UserPo> {

    /**
     * 测试xml方式绑定查询
     */
    @Deprecated
    UserPo findThis(@Param("id") Long id);

    UserPo findByName(@Param("name") String name);

}
