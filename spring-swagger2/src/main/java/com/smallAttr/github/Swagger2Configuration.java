package com.smallAttr.github;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author: chenGang
 * Date: 2018/9/21 下午3:43
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.smallAttr.github.controller"))
        .paths(PathSelectors.any())
        .build();

  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring Boot集成Swagger2")
        .contact(new Contact("smallAttr", "https://github.com/smallAttr", "1140720248@qq.com"))
        .version("1.0.0")
        .description("能不能不让程序员写文档")
        .build();
  }

}
