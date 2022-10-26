package com.example.relaxtest.config;

import com.example.relaxtest.util.UploadedFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config{
    @Bean
    public UploadedFile getFileStream(){
        return new UploadedFile("");
    }

}
