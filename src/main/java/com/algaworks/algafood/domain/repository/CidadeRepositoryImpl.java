package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.respository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


public class CidadeRepositoryImpl  {

    @PersistenceContext
    EntityManager manager;

    public List<Cidade> listar(){
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Transactional
    public Cidade salvar(Cidade cidade){
        return manager.merge(cidade);
    }

    public Cidade buscar(Long id){
        Cidade cidade = manager.find(Cidade.class, id);
        if(cidade != null) return cidade;
        throw new EmptyResultDataAccessException(1);
    }


    @Transactional
    public void remover(Cidade cidade){
        cidade = buscar(cidade.getId());
        manager.remove(cidade);
    }
}
