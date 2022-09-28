package ch.zli.m223.service;


import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.model.Benutzer;
import io.smallrye.jwt.build.Jwt;

public class TokenService {
    public String createToken(Benutzer benutzer) {
        String token = Jwt.issuer("https://github.com/Mathelike72")
                .upn(benutzer.getId().toString())
                .groups(new HashSet<>(Arrays.asList(benutzer.isAdmin() ? "Admin" : "Benutzer")))
                .claim(Claims.email.name(), benutzer.getEMail())
                .sign();
        return token;
    }
}
