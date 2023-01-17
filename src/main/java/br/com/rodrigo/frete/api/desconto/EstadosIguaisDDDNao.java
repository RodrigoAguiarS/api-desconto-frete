package br.com.rodrigo.frete.api.desconto;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import br.com.rodrigo.frete.api.models.Frete;
import br.com.rodrigo.frete.api.models.dto.CepResponse;
import br.com.rodrigo.frete.api.models.enums.Desconto;

public class EstadosIguaisDDDNao extends Verificador{

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "https://viacep.com.br/ws/";

    @Override
    public boolean verifica(String cepOrigem, String cepDestino) {
        CepResponse origem = restTemplate.getForObject(URL+ cepOrigem + "/json/", CepResponse.class);
        CepResponse destino = restTemplate.getForObject(URL + cepDestino + "/json/", CepResponse.class);
        return !origem.getDdd().equals(destino.getDdd()) && origem.getUf().equals(destino.getUf());
    }

    @Override
    public Frete calcular(Frete frete) {
        double valorFrete = frete.getPeso();
        valorFrete = valorFrete - (valorFrete * Desconto.ESTADO_IGUAIS_DDD_DIFERENTE.getPorcentagemDesconto());
            frete.setDataPrevistaEntrega(LocalDate.now().plusDays(Desconto.ESTADO_IGUAIS_DDD_DIFERENTE.getDiasEntrega()));
            frete.setValorTotalFrete(valorFrete);
            return frete;
    } 
}
