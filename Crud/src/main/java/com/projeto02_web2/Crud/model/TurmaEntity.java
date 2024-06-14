package com.projeto02_web2.Crud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projeto02_web2.Crud.DTO.TurmaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "turmas")
@Data
@AllArgsConstructor
public class TurmaEntity {

    public TurmaEntity() {
        this.ativo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String codigo;
    private boolean ativo;

    @ManyToMany
    @JoinTable(
            name = "turma_aluno",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<AlunoEntity> alunos;

    @ManyToOne
    @JoinTable(
            name = "turma_professor",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private ProfessorEntity professor;

    public void putMetodo(TurmaDTO turmaDTO) {
        if (turmaDTO.nome() != null) {
            this.nome = turmaDTO.nome();
        }
        if (turmaDTO.codigo() != null) {
            this.codigo = turmaDTO.codigo();
        }
    }

    public void inativarTurma() {
        this.ativo = false;
    }

    public void ativarTurma() {
        this.ativo = true;
    }
}
