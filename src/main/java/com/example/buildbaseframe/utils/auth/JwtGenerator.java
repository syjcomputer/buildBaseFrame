package com.example.buildbaseframe.utils.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.buildbaseframe.utils.exception.JwtException;
import com.example.buildbaseframe.utils.exception.LogicException;
import com.example.buildbaseframe.utils.exception.NoAuthException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * <b>JWT工具类</b>
 *
 * @author lq
 * @version 1.0
 */
@Slf4j
@Component
public class JwtGenerator implements InitializingBean {

    private static final String PREFIX = "Bearer ";
    private static final String ISSUER = "nwpu.dev";
    private static final String SUBJECT = "buildBaseFrame";
    private static final String USER_ID_KEY = "id";
    private static final String KEY_AUTO_GEN_KEY = "auto-gen";
    private static final String KEY_SPILT_SIGN = "-";

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 私钥文件路径，可为：auto_gen_xxx(此模式公钥同步自动生成,不读取公钥路径)、私钥文件路径
     */
    @Value("${jwt.private-key-path}")
    public String privateKeyPath;
    @Value("${jwt.private-key-output-path:}")
    public String privateKeyOutputPath;
    /**
     * 公钥文件路径，可为：空(不加载公钥模式，不可进行验证)、公钥文件路径
     */
    @Value("${jwt.public-key-path:}")
    public String publicKeyPath;
    @Value("${jwt.public-key-output-path:}")
    public String publicKeyOutputPath;
    /**
     * 令牌有效时间(单位：分钟)
     */
    @Value("${jwt.expiration-minutes}")
    public int expirationMinutes;
    @Value("${jwt.algorithm:RSA}")
    public String algorithm;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (privateKeyPath.startsWith(KEY_AUTO_GEN_KEY + KEY_SPILT_SIGN)) {
            String[] words = privateKeyPath.split(KEY_SPILT_SIGN);
            int keySize = Integer.parseInt(words[words.length - 1]);
            KeyPair keyPair = genKeyPair(algorithm, keySize);
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
            if (StringUtils.hasText(privateKeyOutputPath)) {
                Files.write(Paths.get(privateKeyOutputPath), privateKey.getEncoded());
            }
            if (StringUtils.hasText(publicKeyOutputPath)) {
                Files.write(Paths.get(publicKeyOutputPath), publicKey.getEncoded());
            }
        } else {
            privateKey = loadPrivateKeyFromFile(algorithm, privateKeyPath);
            if (StringUtils.hasText(publicKeyPath)) {
                publicKey = loadPublicKeyFromFile(algorithm, publicKeyPath);
            }
        }
    }

    /**
     * 将 User 基本信息序列化创建 jwt
     */
    public String createJwt(Long userId) {
        Calendar instance = Calendar.getInstance();
        Date now = instance.getTime();
        instance.add(Calendar.MINUTE, expirationMinutes);
        return PREFIX + JWT.create()
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withIssuedAt(now)
                .withExpiresAt(instance.getTime())
                .withClaim(USER_ID_KEY, userId.toString())
                .sign(createAlgorithm());
    }

    /**
     * 验证 jwt token，将 payload 反序列化为指定对象并返回，若验证不通过返回 null
     */
    public <T> T verifyJwt(String token, Class<T> valueType) throws JsonProcessingException {
        if (publicKey == null) {
            throw new LogicException("缺少公钥错误: 验证Jwt要求指定传入公钥");
        }
        if (StringUtils.hasText(token)) {
            if (!token.startsWith(PREFIX)) {
                throw new JwtException("身份令牌格式错误").setJwtMsg("Authorization要求以\"Bearer \"开头");
            }
        } else {
            throw new NoAuthException();
        }

        JWTVerifier jwtVerifier = JWT.require(createAlgorithm())
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withClaimPresence(USER_ID_KEY)
                .build();
        DecodedJWT jwt;
        try {
            jwt = jwtVerifier.verify(token.substring(PREFIX.length()));
        } catch (JWTVerificationException e) {
            log.info("身份令牌无效：{}", e.getMessage());
            throw new JwtException("身份令牌无效", e.getCause()).setJwtMsg(e.getMessage());
        }
        String decoded = new String(Base64.getMimeDecoder().decode(jwt.getPayload()));
        return objectMapper.readValue(decoded, valueType);
    }

    /**
     * 解析 JWT
     */
    public <T> T parseJwt(String token, Class<T> valueType) throws JsonProcessingException {
        if (StringUtils.hasText(token)) {
            if (!token.startsWith(PREFIX)) {
                throw new JwtException("身份令牌格式错误").setJwtMsg("Authorization要求以\"Bearer \"开头");
            }
        } else {
            throw new NoAuthException();
        }

        DecodedJWT decodedJwt = JWT.decode(token.substring(PREFIX.length()));
        String decoded = new String(Base64.getMimeDecoder().decode(decodedJwt.getPayload()));
        return objectMapper.readValue(decoded, valueType);
    }

    private Algorithm createAlgorithm() {
        switch (algorithm) {
            case "RSA":
                return Algorithm.RSA256((RSAPublicKey) publicKey, (RSAPrivateKey) privateKey);
            default:
                throw new LogicException("未知的jwt加密算法");
        }
    }

    private KeyPair genKeyPair(String keyAlgorithm, int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(keyAlgorithm);
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    private static PublicKey loadPublicKeyFromFile(String algorithm, String filePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] pubKeyBytes = Files.readAllBytes(Paths.get(filePath));
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubKeyBytes);
        return keyFactory.generatePublic(pubSpec);
    }

    private static PrivateKey loadPrivateKeyFromFile(String algorithm, String filePath) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] priKeyBytes = Files.readAllBytes(Paths.get(filePath));
        PKCS8EncodedKeySpec priSpec = new PKCS8EncodedKeySpec(priKeyBytes);
        return keyFactory.generatePrivate(priSpec);
    }

    private static String bytes2Base64UTF8(byte[] bytes) {
        return new String(Base64.getMimeEncoder().encode(bytes), StandardCharsets.UTF_8);
    }

}