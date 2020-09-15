package com.mensagens.mensagensapi.repository.service;

import com.mensagens.mensagensapi.model.Cidade;
import com.mensagens.mensagensapi.repository.CidadeRepository;
import com.mensagens.mensagensapi.repository.service.exception.MyHandlerRunTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    private Optional<Cidade> buscarPeloCodigo(Long codigo) {
        Optional<Cidade> cidadeSalva = cidadeRepository.findById(codigo);
        if (cidadeSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return cidadeSalva;
    }

    public Cidade salvar(@Validated Cidade cidade) {

        Optional<Cidade> cidadeExistente= buscarPeloCodigo(cidade.getCodigo());

        if (!cidadeExistente.isPresent()) {
            return cidadeRepository.save(cidade);
        } else {
            throw new MyHandlerRunTimeException("Já existe uma cidade com estes dados!");
        }
    }
}
