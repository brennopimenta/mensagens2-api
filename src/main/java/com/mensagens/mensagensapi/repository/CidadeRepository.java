package com.mensagens.mensagensapi.repository;

import com.mensagens.mensagensapi.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<List<Cidade>> findByEstado(String estado);
}
