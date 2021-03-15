package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntindadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.respository.CozinhaRepository;
import com.algaworks.algafood.domain.respository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscarPorID(@PathVariable Long restauranteId){
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
        if (restaurante.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(restaurante.get());
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
    public ResponseEntity<?> deletar(@PathVariable Long restauranteId){
        try {
            restauranteService.deletar(restauranteId);
        }catch (EntindadeNaoEncontradaException exception){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException exception){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante ){
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
        Optional<Cozinha> cozinhaInformadaNaRequest = cozinhaRepository.findById(restaurante.getCozinha().getId());

        if (restauranteAtual.isPresent() && cozinhaInformadaNaRequest.isPresent()){
            restaurante.setCozinha(cozinhaInformadaNaRequest.get());
            BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
            return ResponseEntity.ok(restauranteAtual.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){

        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
        if(restauranteAtual.isEmpty()) return ResponseEntity.notFound().build();
        merge(campos, restauranteAtual.get());
        return atualizar(restauranteId, restauranteAtual.get());
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @GetMapping("/buscar-por-nome")
    public List<Restaurante> buscarPorNomeECozinha(String nome, Long cozinhaId){
        return restauranteRepository.findByNomeContainsAndCozinhaId(nome, cozinhaId);
    }

    @GetMapping("taxa-frete")
    public List<Restaurante> buscarPorTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween( taxaInicial, taxaFinal);
    }

}
