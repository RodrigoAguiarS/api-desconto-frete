package br.com.rodrigo.frete.api.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.rodrigo.frete.api.models.Frete;
import br.com.rodrigo.frete.api.models.dto.CepResponse;
import br.com.rodrigo.frete.api.models.dto.FreteDto;
import br.com.rodrigo.frete.api.models.enums.Desconto;
import br.com.rodrigo.frete.api.repository.FreteRepository;
import br.com.rodrigo.frete.api.util.ConversorBase;

@Service
public class FreteService {

    private final String URL = "https://viacep.com.br/ws/";

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ConversorBase<Frete, FreteDto> conversor;

    public Frete calcularFrete(FreteDto freteDto) {

        Frete frete = conversor.converterDtoParaEntidade(freteDto);

        // verifica se os estados são iguais com ddd diferente
        boolean estadoIgualComDddDiferente = verificaEstadoIguaisComDddDiferente(frete.getCepOrigem(),
                frete.getCepDestino());
        // verifica se os estados são iguais
        boolean estadosIgualComDddIgual = verificaEstadosIguaisComDddIgual(frete.getCepOrigem(), frete.getCepDestino());

        double valorFrete = frete.getPeso();
        if (estadosIgualComDddIgual) {
            valorFrete = valorFrete - (valorFrete * Desconto.ESTADO_IGUAIS_DDD_IGUAIS.getPorcentagemDesconto());
            frete.setDataPrevistaEntrega(LocalDate.now().plusDays(Desconto.ESTADO_IGUAIS_DDD_IGUAIS.getDiasEntrega()));

        } else if (estadoIgualComDddDiferente) {
            valorFrete = valorFrete - (valorFrete * Desconto.ESTADO_IGUAIS_DDD_DIFERENTE.getPorcentagemDesconto());
            frete.setDataPrevistaEntrega(
                    LocalDate.now().plusDays(Desconto.ESTADO_IGUAIS_DDD_DIFERENTE.getDiasEntrega()));

        } else {
            frete.setDataPrevistaEntrega(LocalDate.now().plusDays(Desconto.ESTADO_DIFERENTES.getDiasEntrega()));
        }

        frete.setValorTotalFrete(valorFrete);
        frete.setDataConsulta(LocalDate.now());
        freteRepository.save(frete);
        return frete;
    }

    private boolean verificaEstadoIguaisComDddDiferente(String cepOrigem, String cepDestino) {
        CepResponse origem = restTemplate.getForObject(URL+ cepOrigem + "/json/", CepResponse.class);
        CepResponse destino = restTemplate.getForObject(URL + cepDestino + "/json/", CepResponse.class);
        return !origem.getDdd().equals(destino.getDdd()) && origem.getUf().equals(destino.getUf());
    }

    private boolean verificaEstadosIguaisComDddIgual(String cepOrigem, String cepDestino) {
        CepResponse origem = restTemplate.getForObject(URL + cepOrigem + "/json/", CepResponse.class);
        CepResponse destino = restTemplate.getForObject(URL + cepDestino + "/json/", CepResponse.class);
        return origem.getUf().equals(destino.getUf()) && origem.getDdd().equals(destino.getDdd());
    }
}
