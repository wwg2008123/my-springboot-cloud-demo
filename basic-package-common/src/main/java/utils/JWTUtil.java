package utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jdk.nashorn.internal.scripts.JD;
import org.springframework.security.core.GrantedAuthority;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class JWTUtil {
    //过期时间为1天
    private static final long Exprire_time = 24 * 60 * 60 * 1000;

    /**
     * 生成签名,指定时间后过期,一经生成不可修改，令牌在指定时间内一直有效
     *
     * @param userNo 用户编号
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String userNo, String secret, List<?> roles) {
        try {
            Date date = new Date(System.currentTimeMillis() + Exprire_time);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("userNo", userNo)
                    .withClaim("roles",roles.toString())
                    .withExpiresAt(date)
                    .sign(algorithm);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param userNo 用户编号
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String userNo, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userNo", userNo)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @param token token中包含的用户名
     * @return
     */
    public static String getUserNo(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String userNo = decodedJWT.getClaim("userNo").asString();
            return userNo;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getRoles(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String roles = decodedJWT.getClaim("roles").asString();
            return roles;
        } catch (Exception e) {
            return null;
        }
    }



}
