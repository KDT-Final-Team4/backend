package com.example.salarying.global.jwt;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetailsModule extends SimpleModule {
    public CustomUserDetailsModule() {
        super();
        addDeserializer(GrantedAuthority.class, new GrantedAuthorityDeserializer());
    }
}