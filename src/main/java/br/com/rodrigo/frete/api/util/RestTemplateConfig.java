package br.com.rodrigo.frete.api.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.com.rodrigo.frete.api.desconto.EstadosDDDIguais;
import br.com.rodrigo.frete.api.desconto.EstadosDiferentes;
import br.com.rodrigo.frete.api.desconto.EstadosIguaisDDDNao;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public EstadosDDDIguais estadosDDDIguais() {
        return new EstadosDDDIguais();
    }

    @Bean
    public EstadosIguaisDDDNao estadosIguaisDDDNao() {
        return new EstadosIguaisDDDNao();
    }

    @Bean
    public EstadosDiferentes estadosDiferente() {
        return new EstadosDiferentes();
    }

    @Bean
    public ConversorBase conversorBase() {
      return new FreteConverter();
    }
}
