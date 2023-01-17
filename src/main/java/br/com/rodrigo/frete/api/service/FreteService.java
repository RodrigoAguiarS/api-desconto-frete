package br.com.rodrigo.frete.api.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodrigo.frete.api.desconto.EstadosDDDIguais;
import br.com.rodrigo.frete.api.desconto.EstadosDiferentes;
import br.com.rodrigo.frete.api.desconto.EstadosIguaisDDDNao;
import br.com.rodrigo.frete.api.models.Frete;
import br.com.rodrigo.frete.api.models.dto.FreteDto;
import br.com.rodrigo.frete.api.repository.FreteRepository;
import br.com.rodrigo.frete.api.util.ConversorBase;

@Service
public class FreteService {

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private ConversorBase<Frete, FreteDto> conversor;

    @Autowired
    private EstadosDDDIguais estadosDDDIguais;

    @Autowired 
    private EstadosIguaisDDDNao estadosIguaisDDDNao;

    @Autowired
    private EstadosDiferentes estadosDiferentes;

    public Frete calcularFrete(FreteDto freteDto) {

        Frete frete = conversor.converterDtoParaEntidade(freteDto);
        frete = estadosDDDIguais.calcular(frete, frete.getCepOrigem(), frete.getCepDestino());
        frete = estadosIguaisDDDNao.calcular(frete, frete.getCepOrigem(), frete.getCepDestino());
        frete = estadosDiferentes.calcular(frete, frete.getCepOrigem(), frete.getCepDestino());
        frete.setDataConsulta(LocalDate.now());
        freteRepository.save(frete);
        return frete;
    }
}
