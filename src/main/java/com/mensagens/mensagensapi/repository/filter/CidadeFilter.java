package com.mensagens.mensagensapi.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CidadeFilter {

    private String nome;
    private String estado;
}
