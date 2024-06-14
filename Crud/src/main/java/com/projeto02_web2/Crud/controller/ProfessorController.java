package com.projeto02_web2.Crud.controller;

import com.projeto02_web2.Crud.DTO.ProfessorDTO;
import com.projeto02_web2.Crud.Service.ProfessorService;
import com.projeto02_web2.Crud.model.ProfessorEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid ProfessorDTO dto) {
        if(dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.cadastrarProfessor(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorID(@PathVariable @NotNull Long id){
        Optional<ProfessorEntity> professor = professorService.buscarPorID(id);
        if(professor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(professor.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO){
        Optional<ProfessorEntity> professor = professorService.buscarPorID(id);
        if(professor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(professorService.atualizarProfessor(id, professorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id){
        Optional<ProfessorEntity> professor = professorService.buscarPorID(id);
        if(professor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado!");
        }
        professorService.deletarProfessor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso!");
    }

    @DeleteMapping("/inativar/{id}")
    public ResponseEntity<Object> inativar(@PathVariable Long id){
        if(!professorService.inativarProfessor(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Professor inativado com sucesso!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> ativar(@PathVariable Long id){
        if(!professorService.ativarProfessor(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Professor ativado com sucesso!");
    }
}

