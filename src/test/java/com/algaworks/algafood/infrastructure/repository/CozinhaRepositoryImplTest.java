package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.respository.CozinhaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class CozinhaRepositoryImplTest {

    @Autowired
    private CozinhaRepository repository;

    @Test
    public void deveRetornarCozinhaNoMeotodoSalvarTest(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Espanhola");
       // Cozinha cozinhaSalva = repository.salvar(cozinha);
      //  Assert.assertEquals(cozinha.getNome(), cozinhaSalva.getNome());
    }

    @Test(expected = ConstraintViolationException.class)
    public void deveRetornarConstraintViolationExceptionParaCozinhaEmpty(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("");
        //Cozinha cozinhaSalva = repository.salvar(cozinha);
    }
}
