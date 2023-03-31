package com.Control;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JWTController {


    public String createJWT(Map<String , Object> claims , String secretKey , long expireDuration) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Date date = new Date();
        JwtBuilder builder = Jwts.builder()
                .setExpiration(new Date(date.getTime()+expireDuration))
                .signWith(signatureAlgorithm, signingKey)
                .addClaims(claims);

        return builder.compact();
    }




    /**
     * This method throws ExpiredJwtException if the token was expired
     */
    public Claims decodeJWT(String jwt , String secretKey) throws SignatureException {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
