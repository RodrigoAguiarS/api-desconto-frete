package br.com.rodrigo.frete.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rodrigo.frete.api.models.Translation;
import br.com.rodrigo.frete.api.repository.TranslationRepository;

@Service
public class TranslationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TranslationRepository translationRepository;

    @Transactional
    public Translation translateText(Long id, String text, String style) throws JsonMappingException, JsonProcessingException {

        Translation translation = translationRepository.findByTextAndStyle(text, style);
        if (translation != null) {
            
            return translation;
        }
        // if not, call the API
        String url = "https://api.funtranslations.com/translate/" + style + ".json?text=" + text;
        String result = restTemplate.getForObject(url, String.class);
        // parse the result to get the translated text
        String translated = parseTranslatedText(result);
        translation = new Translation(id, text, translated, style);
        return translationRepository.save(translation);

    }

    private String parseTranslatedText(String json) throws JsonMappingException, JsonProcessingException {
        
        JsonNode jsonNode = new ObjectMapper().readTree(json);
        return jsonNode.get("contents").get("translated").asText();

    }
}
