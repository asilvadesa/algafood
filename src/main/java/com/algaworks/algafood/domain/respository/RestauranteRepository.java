package com.algaworks.algafood.domain.respository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante buscar(Long id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Long restauranteId);

}
