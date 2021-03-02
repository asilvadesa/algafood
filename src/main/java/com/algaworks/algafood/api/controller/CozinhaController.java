package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.respository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar(){
        return cozinhaRepository.listar();
    }

    @GetMapping(produces =  MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listaXml(){
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if(cozinha != null) return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha cozinha){
        return cozinhaRepository.salvar(cozinha);
    }

}
