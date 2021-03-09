package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.respository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    EntityManager manager;

    @Override
    public List<Restaurante> listar(){
        return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    @Override
    @Transactional
    public Restaurante salvar(Restaurante restaurante){
        return manager.merge(restaurante);
    }

    @Override
    public Restaurante buscar(Long id){
        return manager.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public void remover(Long restauranteId){
        Restaurante restaurante = buscar(restauranteId);
        try{
            manager.remove(restaurante);
        }catch (IllegalArgumentException exception){
            throw new EmptyResultDataAccessException(1);
        }
    }
}
