package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.respository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl  {


    @PersistenceContext
    EntityManager manager;

    public List<Cozinha> listar(){
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    public Cozinha buscar(Long id){
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    public void remover(Long cozinhaId){
        Cozinha cozinha = buscar(cozinhaId);
        try {
            manager.remove(cozinha);
        }catch (IllegalArgumentException exception){
            throw new EmptyResultDataAccessException(1);
        }
    }
}
