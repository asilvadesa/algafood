package com.algaworks.algafood.jpa.cozinha;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.respository.CozinhaRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository
                repository = applicationContext.getBean(CozinhaRepository
                .class);
        List<Cozinha> cozinhas = repository.listar();
        for (Cozinha cozinha:cozinhas) {
            System.out.println(cozinha.getNome());
        }
    }
}
