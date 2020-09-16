package com.mensagens.mensagensapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull(message = "Nome da Cidade não pode ser nulo!")
    @Size(min = 3, max = 30, message = "O nome deve ter entre {min} e {max} caracteres!")
    private String nome;

    @NotNull
    private String estado;
}
