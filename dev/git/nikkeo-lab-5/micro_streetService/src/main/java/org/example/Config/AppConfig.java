package org.example.Config;

import org.example.Converter.Converter;
import org.example.Converter.ConverterRealization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Converter converter() {
        return new ConverterRealization();
    }
}
