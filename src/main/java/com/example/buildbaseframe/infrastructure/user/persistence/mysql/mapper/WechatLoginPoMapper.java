package com.example.buildbaseframe.infrastructure.user.persistence.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.UserPo;
import com.example.buildbaseframe.infrastructure.user.persistence.repository.po.WechatLoginPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName: WechatLoginPoMapper
 * @Description:
 * @Author: syj
 * @Date: 2023/4/8
 * @Version: 1.0
 */
@Mapper
public interface WechatLoginPoMapper  extends BaseMapper<WechatLoginPo> {

    public WechatLoginPo findByOpenId(String openId);

}
