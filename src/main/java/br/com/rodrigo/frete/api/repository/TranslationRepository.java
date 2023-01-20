package br.com.rodrigo.frete.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.frete.api.models.Translation;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {

    Translation findByTextAndStyle(String text, String style);

}
