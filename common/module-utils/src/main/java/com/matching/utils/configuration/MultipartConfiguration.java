package com.matching.utils.configuration;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfiguration {
    private static final Long MAX_FILE_SIZE = 100L;

    @Value("${spring.servlet.multipart.location}")
    private String location;

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        //String location = System.getProperty("user.dir") + File.separator + "data" + File.separator + "resources" + File.separator + "images" + File.separator;

        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(location);
        factory.setMaxRequestSize(DataSize.ofMegabytes(MAX_FILE_SIZE));
        factory.setMaxFileSize(DataSize.ofMegabytes(MAX_FILE_SIZE));

        return factory.createMultipartConfig();
    }
}
