package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntindadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RequisitosNaoAtendidosException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.respository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade) {
        try {
            return cidadeRepository.salvar(cidade);
        }catch (ConstraintViolationException exception){
            throw new RequisitosNaoAtendidosException(String.format("Parametros obrigatórios não foram atendidos"));
        }
    }

    public void remover(Long cidadeId) {
        Cidade cidade = cidadeRepository.buscar(cidadeId);
        cidadeRepository.remover(cidade);
    }
}
