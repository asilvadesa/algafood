package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.respository.CidadeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
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
        return manager.find(Cidade.class, id);
    }

    @Override
    @Transactional
    public void remover(Cidade cidade){
        cidade = buscar(cidade.getId());
        manager.remove(cidade);
    }
}
