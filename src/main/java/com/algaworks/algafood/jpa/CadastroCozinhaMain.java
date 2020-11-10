package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class CadastroCozinhaMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        Cozinha c1 = new Cozinha();
        c1.setNome("Brasileira");

        Cozinha c2 = new Cozinha();
        c2.setNome("Japonesa");

        cadastroCozinha.salvar(c1);
        cadastroCozinha.salvar(c2);

    }
}
