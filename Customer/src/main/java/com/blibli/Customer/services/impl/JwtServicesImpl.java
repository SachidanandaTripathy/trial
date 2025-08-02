package com.blibli.Customer.services.impl;

import com.blibli.Customer.dto.AuthRequest;
import com.blibli.Customer.services.JwtServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServicesImpl implements JwtServices {

    private final String secretKey = "Pbjl2zgK5aFLn26DjHdLJWKzSkPghbs3UxeRLR6PW5c=";

    @Override
    public String generateToken(AuthRequest authRequest) {
        Map<String ,Object> claims=new HashMap<>();
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(authRequest.getCustomerEmail())
                .setIssuer("BLIBLI")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .signWith(generateKey())
                .compact();
    }
    public SecretKey generateKey(){
        byte [] decode= Decoders.BASE64.decode(getSecretKey());
        return Keys.hmacShaKeyFor(decode);
    }
    public String getSecretKey() {
        return secretKey;
    }
}
