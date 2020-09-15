package com.mensagens.mensagensapi.repository.service;

import com.mensagens.mensagensapi.model.Cidade;
import com.mensagens.mensagensapi.repository.CidadeRepository;
import com.mensagens.mensagensapi.repository.service.exception.MyHandlerRunTimeException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    private Cidade buscarPeloCodigo(Long codigo) {
        Cidade cidadeSalva = cidadeRepository.getOne(codigo);
        if (cidadeSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return cidadeSalva;
    }

    public Cidade salvar(@Validated Cidade cidade) {

        Cidade cidadeExistente= buscarPeloCodigo(cidade.getCodigo());

        if (cidadeExistente == null) {
            return cidadeRepository.save(cidade);
        } else {
            throw new MyHandlerRunTimeException("Já existe uma cidade com estes dados!");
        }
    }

    public Cidade atualizar(Long codigo, Cidade cidade) {
        Cidade cidadeSalva = buscarPeloCodigo(codigo);
        BeanUtils.copyProperties(cidade, cidadeSalva, "codigo");
        return cidadeRepository.save(cidadeSalva);
    }

}
