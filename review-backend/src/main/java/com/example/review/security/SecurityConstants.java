package com.example.review.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "Rjw&#k}7iTr,UI?Oux~:08VNg](Jf8Ue*m>$VEF?O,z7:yhp0Osw6l#p/`==K?9["; //Your secret should always be strong (uppercase, lowercase, numbers, symbols) so that nobody can potentially decode the signature.
    public static final int TOKEN_EXPIRATION = 7200000; // 7200000 milliseconds = 7200 seconds = 2 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token 
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
}
