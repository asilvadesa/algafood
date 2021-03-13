package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntindadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.respository.CozinhaRepository;
import com.algaworks.algafood.domain.respository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;


    public Restaurante salvar(Restaurante restaurante){
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(()-> new EntindadeNaoEncontradaException(
                        String.format("Nao existe cadastro de cozinha com esse codigo: %d", cozinhaId)));
        restaurante.setCozinha(cozinha);
        return restauranteRepository.salvar(restaurante);
    }

    public void deletar(Long restauranteId) {
        try {
            restauranteRepository.remover(restauranteId);
        }catch (EmptyResultDataAccessException exception){
            throw new EntindadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", restauranteId));
        }catch (DataIntegrityViolationException exception){
            throw new EntidadeEmUsoException(exception.getMessage());
        }
    }
}
