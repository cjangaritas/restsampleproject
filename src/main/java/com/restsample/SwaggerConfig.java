package com.restsample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

@Configuration
//@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {

//
//    private SpringSwaggerConfig springSwaggerConfig;
//
//    @Autowired
//    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
//        this.springSwaggerConfig = springSwaggerConfig;
//    }
//
//    @Bean
//    public SwaggerSpringMvcPlugin customImplementation() {
//        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
//                //This info will be used in Swagger. See realisation of ApiInfo for more details.
//                .apiInfo(new ApiInfo(
//                        "Festivities Rest Sample Project",
//                        "This application exposes a REST API for festivities",
//                        null,
//                        null,
//                        null,
//                        null
//                ))
//                //Here we disable auto generating of responses for REST-endpoints
//                .useDefaultResponseMessages(false)
//                //Here we specify URI patterns which will be included in Swagger docs. Use regex for this purpose.
//                .includePatterns("/festivity.*");
//    }

}