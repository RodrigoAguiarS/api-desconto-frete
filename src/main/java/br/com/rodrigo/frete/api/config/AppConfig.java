package br.com.rodrigo.frete.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class AppConfig {

    @Value("${cep.url}")
    private String cepUrl;  
}
