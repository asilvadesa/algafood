package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.RequisitosNaoAtendidosException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.respository.CidadeRepository;
import com.algaworks.algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscaPorId(@PathVariable Long cidadeId){
        try{
            Cidade cidade = cidadeRepository.buscar(cidadeId);
            return ResponseEntity.ok(cidade);
        }catch (EmptyResultDataAccessException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade){
        try {
            cidade = cidadeService.salvar(cidade);
            return ResponseEntity.ok(cidade);
        }catch (RequisitosNaoAtendidosException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<?> deletar(@PathVariable Long cidadeId){
        try {
            cidadeService.remover(cidadeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        }catch (EmptyResultDataAccessException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
