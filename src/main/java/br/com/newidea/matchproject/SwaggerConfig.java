package br.com.newidea.matchproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) //apenas classes anotadas como @RestController
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("API REST - Application MatchProject")
                .description("Através desta API será possível efetuar a manutenção e obtenção de diversos recursos do aplicativo MatchProject.")
                .termsOfServiceUrl("https://github.com/fabiomirgo")
                .contact(new Contact("Fabio Miranda Gonçalves", "https://www.linkedin.com/in/fabio-miranda-gon%C3%A7alves-b3185097/", "fabio.mirgo@gmail.com"))
                .license("Licença")
                .licenseUrl("https://github.com/fabiomirgo")
                .version("1.0.0-SNAPSHOT")
                .build();
    }
}