package com.algaworks.algafood.jpa.permissao;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.respository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class BuscarPermissoaMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissaoRepository
                repository = applicationContext.getBean(PermissaoRepository
                .class);

        Permissao permissao = repository.buscar(1L);

        System.out.println(permissao.getDescricao());

    }
}
