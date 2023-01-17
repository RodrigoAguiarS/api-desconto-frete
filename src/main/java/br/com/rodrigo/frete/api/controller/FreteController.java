package br.com.rodrigo.frete.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.frete.api.models.Frete;
import br.com.rodrigo.frete.api.models.dto.FreteDto;
import br.com.rodrigo.frete.api.service.FreteService;
import br.com.rodrigo.frete.api.util.ConversorBase;

@RestController
@RequestMapping("/frete")
public class FreteController {

    @Autowired
    private FreteService freteService;

    @Autowired
    private ConversorBase<Frete, FreteDto> conversor;

    @PostMapping
    public ResponseEntity<FreteDto> calcularFrete(@RequestBody FreteDto freteDto) {
        Frete frete = conversor.converterDtoParaEntidade(freteDto);
        frete = freteService.calcularFrete(freteDto);
        freteDto = conversor.converterEntidadeParaDto(frete);
        return new ResponseEntity<>(freteDto, HttpStatus.OK);
    }
}
