package org.slam.slam_backend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Map;

@Log4j2
public class JWTUtil {

     private static String key = "121341241324345f43j9f43j9f43j9f43f435435213243241";

     public static String generateToken(Map<String, Object> valueMap, int min) {

         SecretKey key = null;
         try{
             key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
         }catch(Exception e){
             throw new RuntimeException(e.getMessage());
         }
         String jwtStr = Jwts.builder()
                 .setHeader(Map.of("typ","JWT"))
                 .setClaims(valueMap)
                 .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                 .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                 .signWith(key)
                 .compact();
         return jwtStr;
     }

     public static Map<String, Object> validateToken(String token) {
            Map<String, Object> claim = null;

            try{
                SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));

                claim = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

            }catch(MalformedJwtException ex) {
                throw new CustomJWTException("malformed");
            }catch(ExpiredJwtException ex) {
                throw new CustomJWTException("expired");
            }catch(InvalidClaimException ex) {
                throw new CustomJWTException("invalid");
            }catch(JwtException ex) {
                throw new CustomJWTException("JwtError");
            }catch(Exception ex) {
                throw new CustomJWTException("error");
            }
            return claim;
     }

}
