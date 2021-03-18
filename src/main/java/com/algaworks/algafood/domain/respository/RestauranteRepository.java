package com.algaworks.algafood.domain.respository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInifical, BigDecimal taxaFinal);
    List<Restaurante> findByNomeContainsAndCozinhaId(String nome, Long cozinha);
    Optional<Restaurante> findFirstByNomeContaining(String nome);
    List<Restaurante> findTop2ByNomeContaining(String nome);
    int countByCozinhaId(Long cozinhaId);
}
