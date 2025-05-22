package com.projeto02_web2.Crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto02_web2.Crud.DTO.AlunoDTO;
import com.projeto02_web2.Crud.enums.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="alunos")
@Data
@AllArgsConstructor
public class AlunoEntity {

    public AlunoEntity() {
        this.ativo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private int matricula;
    private Genero genero;
    private String curso;
    private String dataNascimento;
    private boolean ativo;

    @ManyToMany(mappedBy = "alunos", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TurmaEntity> turmas;

    public void putMetodo(AlunoDTO alunoDTO) {
        if(alunoDTO.nome() != null){
            this.nome = alunoDTO.nome();
        }
        if(alunoDTO.cpf() != null){
            this.cpf = alunoDTO.cpf();
        }
        if(alunoDTO.matricula() != 0){
            this.matricula = alunoDTO.matricula();
        }
        if(alunoDTO.genero() != null){
            this.genero = alunoDTO.genero();
        }
        if(alunoDTO.curso() != null){
            this.curso = alunoDTO.curso();
        }
        if(alunoDTO.dataNascimento() != null) {
            this.dataNascimento = alunoDTO.dataNascimento();
        }
    }

    public void inativarAluno() {
        this.ativo = false;
    }

    public void ativarAluno() {
        this.ativo = true;
    }
}
