package br.com.rodrigo.frete.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodrigo.frete.api.models.Frete;

public interface FreteRepository extends JpaRepository<Frete, Long> {
    
}