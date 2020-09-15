package com.mensagens.mensagensapi.repository.cidade;

import com.mensagens.mensagensapi.model.Cidade;
import com.mensagens.mensagensapi.repository.filter.CidadeFilter;

import java.util.List;

public interface CidadeRepositoryQuery {

    List<Cidade> filtrar(CidadeFilter cidadeFilter);
}
