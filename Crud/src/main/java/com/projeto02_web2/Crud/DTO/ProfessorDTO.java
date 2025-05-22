package com.projeto02_web2.Crud.DTO;

import com.projeto02_web2.Crud.enums.Genero;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorDTO (

        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "CPF é obrigatório")
        String cpf,
        @NotNull
        int matricula,
        @Enumerated(EnumType.STRING)
        Genero genero,
        @NotBlank(message = "Departamento é obrigatório")
        String departamento,
        @NotBlank(message = "Data de nascimento é obrigatória")
        String dataNascimento,
        @NotNull
        float salario){
}
