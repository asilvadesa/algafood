package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.exception.EntindadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.respository.EstadoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EstadoRepositoryImpl{

    @PersistenceContext
    EntityManager manager;

    public List<Estado> listar(){
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }


    @Transactional
    public Estado salvar(Estado estado){
        return manager.merge(estado);
    }

    public Estado buscar(Long id){
        Estado estado = manager.find(Estado.class, id);
        if(estado != null) return estado;
        throw new EntindadeNaoEncontradaException(String.format("Entidade não entrada com esse id: %d", id));
    }

    @Transactional
    public void remover(Long estadoId){
        Estado estado = buscar(estadoId);
        manager.remove(estado);
    }
}
