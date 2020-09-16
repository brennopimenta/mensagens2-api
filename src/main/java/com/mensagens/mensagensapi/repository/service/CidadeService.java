package com.mensagens.mensagensapi.repository.service;

import com.mensagens.mensagensapi.model.Cidade;
import com.mensagens.mensagensapi.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    private Cidade buscarPeloCodigo(Long codigo) {
        return cidadeRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public Cidade atualizar(Long codigo, Cidade cidade) {
        Cidade cidadeSalva = buscarPeloCodigo(codigo);
        BeanUtils.copyProperties(cidade, cidadeSalva, "codigo");
        return cidadeRepository.save(cidadeSalva);
    }
}
