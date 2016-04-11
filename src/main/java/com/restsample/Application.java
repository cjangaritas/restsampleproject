package com.restsample;

import com.restsample.xml.SimpleXmlMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.restsample")
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext app = SpringApplication.run(Application.class, args);
        Application.mapXml(app);
    }

    private static void mapXml(ConfigurableApplicationContext applicationContext) {
        // XML Initialization
        SimpleXmlMapper mapper = (SimpleXmlMapper)applicationContext.getBean("simpleXmlMapper");
        mapper.executeXmlDataImport();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }

}