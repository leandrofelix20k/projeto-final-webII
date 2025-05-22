package com.projeto02_web2.Crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projeto02_web2.Crud.DTO.ProfessorDTO;
import com.projeto02_web2.Crud.enums.Genero;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="professores")
@Data
@AllArgsConstructor
public class ProfessorEntity {

    public ProfessorEntity() {
        this.ativo = true;
        this.disciplinaAssociada = "Nenhuma disciplina associada";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private int matricula;
    private Genero genero;
    private String departamento;
    private String dataNascimento;
    private float salario;
    private String disciplinaAssociada;
    private boolean ativo;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TurmaEntity> turmas;

    public void putMetodo(ProfessorDTO professorDTO) {
        if(professorDTO.nome() != null){
            this.nome = professorDTO.nome();
        }
        if(professorDTO.cpf() != null){
            this.cpf = professorDTO.cpf();
        }
        if(professorDTO.matricula() != 0){
            this.matricula = professorDTO.matricula();
        }
        if(professorDTO.genero() != null){
            this.genero = professorDTO.genero();
        }
        if(professorDTO.departamento() != null){
            this.departamento = professorDTO.departamento();
        }
        if(professorDTO.dataNascimento() != null) {
            this.dataNascimento = professorDTO.dataNascimento();
        }
        if(professorDTO.salario() != 0) {
            this.salario = professorDTO.salario();
        }
    }

    public void inativarProfessor() {
        this.ativo = false;
    }

    public void ativarProfessor() {
        this.ativo = true;
    }

    public void atualizarDisciplinaAssociada(){
        if(this.turmas.isEmpty()){
            this.disciplinaAssociada = "Nenhuma disciplina associada";
        } else {
            this.disciplinaAssociada = turmas.stream()
                    .map(TurmaEntity::getNome)
                    .collect(Collectors.joining(", "));
        }
    }

}