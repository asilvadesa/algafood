package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntindadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.respository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurante> listar(){
        return restauranteRepository.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscarPorID(@PathVariable Long restauranteId){
        Restaurante restaurante = restauranteRepository.buscar(restauranteId);
        if (restaurante == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante){
        try{
            Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
        }catch (EntindadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> deletar(@PathVariable Long restauranteId){
        try {
            restauranteService.deletar(restauranteId);
        }catch (EntindadeNaoEncontradaException exception){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
