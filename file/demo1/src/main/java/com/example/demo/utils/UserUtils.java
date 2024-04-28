package com.example.demo.utils;



import com.example.demo.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;



public class UserUtils {
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static final String SECRET_KEY = "secretKeyUser";

    // 生成用户token
    public static String generateUserToken(Integer userid, String username, String password, String role, Integer departmentid, String name) {
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        return Jwts.builder()
                .claim("userid", userid)
                .claim("username", username)
                .claim("password", password)
                .claim("role", role)
                .claim("departmentid", departmentid)
                .claim("name", name)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // 获取token中的用户名
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 获取token中的用户信息
    public static Users getUserInfoFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        // 从claims中提取用户信息

        Integer userid = (Integer) claims.get("userid");
        String username = (String) claims.get("username");
        String password = (String) claims.get("password");
        String role = (String) claims.get("role");
        Integer departmentid = (Integer) claims.get("departmentid");
        String name = (String) claims.get("name");


        return new Users(userid, username, password, role, departmentid, name);
    }

    // 判断token是否有效
    public static boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
