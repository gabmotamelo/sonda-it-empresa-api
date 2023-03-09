package sonda.it.empresaapi.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    private static final String BASE_PACKAGE = "sonda.it.empresaapi";
    private static final String API_TITLE = "Api de consulta a dados de empresas.";
    private static final String CONTACT_NAME = "Gabriel Mota Melo";
    private static final String CONTACT_GITHUB = "https://github.com/gabmotamelo";
    private static final String CONTACT_EMAIL = "gabrielmota6@hotmail.com";

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group(API_TITLE)
                .group(CONTACT_NAME)
                .packagesToScan(BASE_PACKAGE)
                .build();
    }

}
