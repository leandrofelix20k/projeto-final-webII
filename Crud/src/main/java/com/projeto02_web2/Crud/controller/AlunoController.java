package com.projeto02_web2.Crud.controller;

import com.projeto02_web2.Crud.DTO.AlunoDTO;
import com.projeto02_web2.Crud.Service.AlunoService;
import com.projeto02_web2.Crud.model.AlunoEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid AlunoDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.cadastrarAluno(dto));
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorID(@PathVariable @NotNull Long id){
        Optional<AlunoEntity> aluno = alunoService.buscarPorID(id);
        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(alunoService.buscarPorID(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO){
        Optional<AlunoEntity> aluno = alunoService.buscarPorID(id);
        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(alunoService.atualizarAluno(id, alunoDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id){
        Optional<AlunoEntity> aluno = alunoService.buscarPorID(id);
        if(aluno.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        alunoService.deletarAluno(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

    @DeleteMapping("/inativar/{id}")
    public ResponseEntity<Object> inativar(@PathVariable Long id){
        if(!alunoService.inativarAluno(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Aluno inativado com sucesso!");
    }

    @PatchMapping("{id}")
    public ResponseEntity<Object> ativar(@PathVariable Long id){
        if(!alunoService.ativarAluno(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Aluno ativado com sucesso!");
    }
}
