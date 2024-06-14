package com.projeto02_web2.Crud.DTO;

import com.projeto02_web2.Crud.model.AlunoEntity;
import com.projeto02_web2.Crud.model.ProfessorEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TurmaDTO (

        @NotNull
        Long id,
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Código é obrigatório")
        String codigo
){}
