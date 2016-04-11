package com.restsample;

import com.restsample.service.FestivityService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    public FestivityService festivityService() {
        return Mockito.mock(FestivityService.class);
    }
}