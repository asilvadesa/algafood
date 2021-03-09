package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.respository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Cidade> listar(){
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    @Transactional
    public Cidade salvar(Cidade cidade){
        return manager.merge(cidade);
    }

    @Override
    public Cidade buscar(Long id){
        Cidade cidade = manager.find(Cidade.class, id);
        if(cidade != null) return cidade;
        throw new EmptyResultDataAccessException(1);
    }

    @Override
    @Transactional
    public void remover(Cidade cidade){
        cidade = buscar(cidade.getId());
        manager.remove(cidade);
    }
}
