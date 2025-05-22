package com.projeto02_web2.Crud.DTO;

import com.projeto02_web2.Crud.enums.Genero;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "CPF é obrigatório")
        String cpf,
        @NotNull
        int matricula,
        @Enumerated(EnumType.STRING)
        Genero genero,
        @NotBlank(message = "Curso é obrigatório")
        String curso,
        @NotBlank(message = "Data de nascimento é obrigatória")
        String dataNascimento) {
}