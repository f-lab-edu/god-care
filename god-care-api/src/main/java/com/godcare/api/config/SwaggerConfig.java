package com.godcare.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@EnableWebMvc
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.godcare.api.controller")) // //  Swagger API 문서로 만들기 원하는 BasePackage 경로. (필수)
                .paths(PathSelectors.any()) // URL 경로를 지정하여 해당 URL에 해당하는 요청만 Swagger API 문서로 만든다. (any()로 설정 시 모든 api가 보여짐, (필수))
                .build()
                .apiInfo(apiInfo()) // Swagger API 문서에 대한 설명을 표기하는 메소드. (선택)
                .consumes(Collections.singleton(MediaType.MULTIPART_FORM_DATA_VALUE)) // 서버로 보낼 데이터의 Content-type을 설정
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_VALUE)); // 클라이언트에게 전송할 데이터의 Content-type을 설정
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("God Care")
                .description("God-Care API 리스트입니다.")
                .version("1.0")
                .build();
    }


}
