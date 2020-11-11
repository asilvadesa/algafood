package com.algaworks.algafood.jpa.formapagamento;

import com.algaworks.algafood.AlgafoodApplication;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.respository.FormaPagamentoRepository;
import com.algaworks.algafood.respository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class BuscarFormaPagamentoMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                new SpringApplicationBuilder(AlgafoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormaPagamentoRepository
                repository = applicationContext.getBean(FormaPagamentoRepository
                .class);

        FormaPagamento formaPagamento = repository.buscar(1L);

        System.out.println(formaPagamento.getDescricao());

    }
}
