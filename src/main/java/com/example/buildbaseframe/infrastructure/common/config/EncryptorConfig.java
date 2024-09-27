package com.example.buildbaseframe.infrastructure.common.config;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <b>全局加密器</b>
 *
 * @author lq
 * @version 1.0
 */
@Configuration
public class EncryptorConfig {

    @Bean
    public SymmetricCrypto getSymmetricCrypto() {
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        return SecureUtil.aes(key);
    }

}
