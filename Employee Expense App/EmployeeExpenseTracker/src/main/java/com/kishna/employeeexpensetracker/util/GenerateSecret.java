package com.kishna.employeeexpensetracker.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class GenerateSecret {

    public static void main(String[] args) {

        String secret = Base64.getEncoder()
                .encodeToString(
                    Keys.secretKeyFor(SignatureAlgorithm.HS256)
                        .getEncoded()
                );

        System.out.println("Your JWT Secret Key:");
        System.out.println(secret);
    }
}