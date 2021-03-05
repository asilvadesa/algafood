package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.exception.EntindadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.respository.EstadoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Estado> listar(){
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    @Transactional
    public Estado salvar(Estado estado){
        return manager.merge(estado);
    }

    @Override
    public Estado buscar(Long id){
        Estado estado = manager.find(Estado.class, id);
        if(estado != null) return estado;
        throw new EntindadeNaoEncontradaException(String.format("Entidade não entrada com esse id: %d", id));
    }

    @Override
    @Transactional
    public void remover(Long estadoId){
        Estado estado = buscar(estadoId);
        manager.remove(estado);
    }
}
