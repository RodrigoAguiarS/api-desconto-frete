package br.com.rodrigo.frete.api.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
 
  @Bean
  public ConversorBase conversorBase() {
    return new FreteConverter();
  }
}
