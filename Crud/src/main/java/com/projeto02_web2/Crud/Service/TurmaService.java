package com.projeto02_web2.Crud.Service;

import com.projeto02_web2.Crud.DTO.TurmaDTO;
import com.projeto02_web2.Crud.model.AlunoEntity;
import com.projeto02_web2.Crud.model.ProfessorEntity;
import com.projeto02_web2.Crud.model.TurmaEntity;
import com.projeto02_web2.Crud.repository.AlunoRepository;
import com.projeto02_web2.Crud.repository.ProfessorRepository;
import com.projeto02_web2.Crud.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public TurmaEntity criarTurma(TurmaDTO dto){
        TurmaEntity turma = new TurmaEntity();
        BeanUtils.copyProperties(dto, turma);

        return turmaRepository.save(turma);
    }

    public Object buscarTodasTurmas() {
        return turmaRepository.findAllByAtivoTrue();
    }

    public Object matricularAluno(Long turmaId, Long alunoId) {
        Optional<TurmaEntity> turma = turmaRepository.findById(turmaId);
        Optional<AlunoEntity> aluno = alunoRepository.findById(alunoId);

        if(turma.isEmpty() || aluno.isEmpty()){
            return "Turma ou aluno não encontrado!";
        }
        turma.get().getAlunos().add(aluno.get());
        turmaRepository.save(turma.get());
        return "Aluno matriculado com sucesso!";
    }

    public Object alocarProfessor(Long turmaId, Long professorId) {
        Optional<TurmaEntity> turma = turmaRepository.findById(turmaId);
        Optional<ProfessorEntity> professor = professorRepository.findById(professorId);

        if(turma.isEmpty() || professor.isEmpty()){
            return "Turma ou professor não encontrado!";
        }

        if(turma.get().getProfessor() != null){
            ProfessorEntity professorAtual = turma.get().getProfessor();
            professorAtual.getTurmas().remove(turma.get());
            professorAtual.atualizarDisciplinaAssociada();
            professorRepository.save(professorAtual);
        }

        turma.get().setProfessor(professor.get());
        professor.get().getTurmas().add(turma.get());
        professor.get().atualizarDisciplinaAssociada();

        turmaRepository.save(turma.get());
        professorRepository.save(professor.get());

        return "Professor alocado com sucesso!";
    }

    public Object buscarPorID(Long id) {
        Optional<TurmaEntity> turma = turmaRepository.findById(id);
        if(turma.isEmpty()){
            return "Turma não encontrada!";
        }
        return turma.get();
    }

    public Object atualizarTurma(Long id, TurmaDTO turmaDTO) {
        Optional<TurmaEntity> turma = turmaRepository.findById(id);
        if(turma.isEmpty()){
            return "Turma não encontrada!";
        }

        TurmaEntity turmaEntity = turma.get();
        turmaEntity.putMetodo(turmaDTO);
        turmaRepository.save(turmaEntity);
        return turmaEntity;
    }

    public Object deletarTurma(Long id) {
        Optional<TurmaEntity> turma = turmaRepository.findById(id);
        if(turma.isEmpty()){
            return "Turma não encontrada!";
        }

        if(turma.get().getProfessor() != null){
            ProfessorEntity professor = turma.get().getProfessor();
            professor.getTurmas().remove(turma.get());
            professor.atualizarDisciplinaAssociada();
            professorRepository.save(professor);
        }

        for(AlunoEntity aluno : turma.get().getAlunos()){
            aluno.getTurmas().remove(turma.get());
            alunoRepository.save(aluno);
        }

        turmaRepository.delete(turma.get());
        return "Turma deletada com sucesso!";
    }

    public Object inativarTurma(Long id) {
        Optional<TurmaEntity> turma = turmaRepository.findById(id);
        if(turma.isEmpty()){
            return "Turma não encontrada!";
        }

        turma.get().inativarTurma();
        turmaRepository.save(turma.get());
        return "Turma inativada com sucesso!";
    }

    public Object ativarTurma(Long id) {
        Optional<TurmaEntity> turma = turmaRepository.findById(id);
        if(turma.isEmpty()){
            return "Turma não encontrada!";
        }

        turma.get().ativarTurma();
        turmaRepository.save(turma.get());
        return "Turma ativada com sucesso!";
    }
}
