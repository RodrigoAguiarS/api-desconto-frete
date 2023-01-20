package br.com.rodrigo.frete.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.rodrigo.frete.api.models.Translation;
import br.com.rodrigo.frete.api.service.TranslationService;

@RestController
@RequestMapping("/{style}")
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @GetMapping
    public ResponseEntity<Translation> translateText(@PathVariable("style") String style, @RequestParam("text") String text) throws JsonMappingException, JsonProcessingException {
            Translation result = translationService.translateText(null, text, style);
            return ResponseEntity.ok(result);
        
    }
}
