package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntindadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.respository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public void deletar(Long estadoId) {
        try {
            estadoRepository.remover(estadoId);
        }catch (IllegalArgumentException exception){
            throw new EntindadeNaoEncontradaException(String.format("Entidade não encontrada com esse id: %d", estadoId));
        } catch (DataIntegrityViolationException exception){
            throw new EntidadeEmUsoException(String.format("Estado de código: %d, não pode ser excluída " +
                    "pois está relacionada com outras entidades", estadoId ));
        }
    }
}
