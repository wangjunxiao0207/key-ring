package com.key.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.key.common.constant.Constants;
import com.key.util.DateTimeUtil;
import com.key.util.JsonMapper;
import com.key.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 对登录的用户进行签名和解密
 */
public class LoginJWT {

    // 加密私钥
    private static String SECRET = "ZXcj@#$@#$#@K";
    // token有效期
    private static Long expireTimestamp = 60*60 * 1000L;

    static {
        SECRET = PropertiesUtil.getProperty("jwt.secret");
        expireTimestamp = Long.parseLong(PropertiesUtil.getProperty("jwt.expireTimestamp"));
    }

    /**
     * 对登录用户进行加密
     * @param object 要签名的对象
     * @param maxAge token 生效时长
     * @param <T>
     * @return
     */
    public static <T> String sign(T object, long maxAge) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Map<String, Object> map = new HashMap<String, Object>();
            Date expireDate = DateTimeUtil.getDate(new Date().getTime() + expireTimestamp);

            map.put("alg", "HS256");
            map.put("typ", "JWT");

            String token = JWT.create()
                    /*设置头部信息 Header*/
                    .withHeader(map)
                    /*设置 载荷 Payload*/
                    .withClaim(Constants.JWTPayload.EXP, System.currentTimeMillis() + expireTimestamp)
                    .withClaim(Constants.JWTPayload.PAYLOAD, JsonMapper.obj2String(object))
                    // 剩下的不重要了
                    .withIssuer("SERVICE")//签名生成者
                    .withSubject("loginToken")//签名主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience("CLIENT")//签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(new Date()) // 生成签名的时间
                    .withExpiresAt(expireDate)// 签名过期的时间
                    /*签名 Signature */
                    .sign(algorithm);

            return token;
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 解密，获取用户对象
     * @param jwtToken  加过密的token
     * @param classT   要获取的结果类型
     * @param <T>
     * @return
     */
    public static <T> T unsign(String jwtToken, Class<T> classT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("SERVICE")
                    .build();

            DecodedJWT jwt = verifier.verify(jwtToken);
            // 校验是不是登录token
            String subject = jwt.getSubject();
            if(!StringUtils.equals("loginToken", subject)) {
                return null;
            }

            Map<String, Claim> claims = jwt.getClaims();
            if (claims.containsKey(Constants.JWTPayload.EXP) && claims.containsKey(Constants.JWTPayload.PAYLOAD)) {
                Claim expClaim = claims.get(Constants.JWTPayload.EXP);
                long exp = expClaim.asLong();

                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = claims.get(Constants.JWTPayload.PAYLOAD).asString();
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
