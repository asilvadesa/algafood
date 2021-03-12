package com.algaworks.algafood.api.controller;


import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.respository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@WebMvcTest(CozinhaController.class)
public class CozinhaControllerTest {

    @Autowired
    private CozinhaController cozinhaController;

    @MockBean
    private CozinhaService cozinhaService;

    @MockBean
    private CozinhaRepository cozinhaRepository;

    @BeforeEach
    public void setup(){
        standaloneSetup(this.cozinhaController);
    }


    @Test
    public void deve_RetornarSucesso_QuandoBuscarCozinhaPorId(){

        Cozinha c1 = new Cozinha();
        c1.setId(1L);
        c1.setNome("Brasileira");

        Mockito.when(this.cozinhaRepository.buscar(1L)).thenReturn(c1);

        given()
                .accept(ContentType.JSON)
        .when().get("/cozinhas/{cozinhaId}", 1L)
        .then().statusCode(HttpStatus.OK.value());

    }

}
