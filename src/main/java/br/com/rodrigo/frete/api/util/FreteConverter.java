package br.com.rodrigo.frete.api.util;

import br.com.rodrigo.frete.api.models.Frete;
import br.com.rodrigo.frete.api.models.dto.FreteDto;

public class FreteConverter extends ConversorBase<Frete, FreteDto>{

    @Override
    public FreteDto converterEntidadeParaDto(Frete frete) {
        FreteDto dto = new FreteDto();
        dto.setId(frete.getId());
        dto.setPeso(frete.getPeso());
        dto.setCepOrigem(frete.getCepOrigem());
        dto.setCepDestino(frete.getCepDestino());
        dto.setNomeDestinatario(frete.getNomeDestinatario());
        dto.setValorTotalFrete(frete.getValorTotalFrete());
        dto.setDataPrevistaEntrega(frete.getDataPrevistaEntrega());
        dto.setDataConsulta(frete.getDataConsulta());
        return dto;
    }

    @Override
    public Frete converterDtoParaEntidade(FreteDto freteDto) {
        Frete frete = new Frete();
        frete.setId(freteDto.getId());
        frete.setPeso(freteDto.getPeso());
        frete.setCepOrigem(freteDto.getCepOrigem());
        frete.setCepDestino(freteDto.getCepDestino());
        frete.setNomeDestinatario(freteDto.getNomeDestinatario());
        frete.setValorTotalFrete(freteDto.getValorTotalFrete());
        frete.setDataPrevistaEntrega(freteDto.getDataPrevistaEntrega());
        frete.setDataConsulta(freteDto.getDataConsulta());
        return frete;
    } 
}
