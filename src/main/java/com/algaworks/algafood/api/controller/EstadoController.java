package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntindadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.respository.EstadoRepository;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscaPorId(@PathVariable Long estadoId){
        try{
            Estado estado = estadoRepository.buscar(estadoId);
            return ResponseEntity.ok(estado);
        } catch (EntindadeNaoEncontradaException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
        try {
            estado = estadoService.salvar(estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(estado);
        }catch (ConstraintViolationException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> deletar(@PathVariable Long estadoId){
        try {
            estadoService.deletar(estadoId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (EntindadeNaoEncontradaException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }catch (EntidadeEmUsoException exception){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        try{
            Estado estadoResultadoDaBusca = estadoRepository.buscar(estadoId);
            BeanUtils.copyProperties(estado, estadoResultadoDaBusca, "id");
            Estado estadoSalvo = estadoService.salvar(estadoResultadoDaBusca);
            return ResponseEntity.ok(estadoSalvo);
        }catch (IllegalArgumentException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (TransactionSystemException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
