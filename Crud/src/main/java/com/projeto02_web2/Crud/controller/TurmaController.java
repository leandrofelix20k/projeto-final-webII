package com.projeto02_web2.Crud.controller;

import com.projeto02_web2.Crud.DTO.TurmaDTO;
import com.projeto02_web2.Crud.Service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Object> criarTurma(@RequestBody @Valid TurmaDTO dto){
        if(dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.criarTurma(dto));
    }

    @PostMapping("/{turmaId}/alunos/{alunoId}")
    public ResponseEntity<Object> matricularAluno(@PathVariable Long turmaId, @PathVariable Long alunoId) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.matricularAluno(turmaId, alunoId));
    }

    @PostMapping("/{turmaId}/professores/{professorId}")
    public ResponseEntity<Object> alocarProfessor(@PathVariable Long turmaId, @PathVariable Long professorId) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.alocarProfessor(turmaId, professorId));
    }

    @GetMapping
    public ResponseEntity<Object> buscarTurmas() {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.buscarTodasTurmas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorID(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.buscarPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarTurma(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO){
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.atualizarTurma(id, turmaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarTurma(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.deletarTurma(id));
    }

    @DeleteMapping("/inativar/{id}")
    public ResponseEntity<Object> inativarTurma(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.inativarTurma(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> ativarTurma(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.ativarTurma(id));
    }
}
