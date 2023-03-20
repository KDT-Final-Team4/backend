package com.example.salarying.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        String tokenName = "jwtAuth";
        // API 요청헤더에 스키마 추가
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(tokenName);
        // SecuritySchemes 에 등록
        Components components = new Components()
                .addSecuritySchemes(tokenName, new SecurityScheme()
                        .name(tokenName)
                        .type(SecurityScheme.Type.HTTP) // 스키마 타입 지정
                        .scheme("bearer"));
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("샐러링 채용 프로젝트")
                        .version(appVersion)
                        .description("채용알림 API "))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}

