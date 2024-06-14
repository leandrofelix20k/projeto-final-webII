package com.projeto02_web2.Crud.Service;

import com.projeto02_web2.Crud.DTO.AlunoDTO;
import com.projeto02_web2.Crud.model.AlunoEntity;
import com.projeto02_web2.Crud.model.TurmaEntity;
import com.projeto02_web2.Crud.repository.AlunoRepository;
import com.projeto02_web2.Crud.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    public AlunoEntity cadastrarAluno(AlunoDTO dto) {
        AlunoEntity aluno = new AlunoEntity();
        BeanUtils.copyProperties(dto, aluno);
        return alunoRepository.save(aluno);
    }


    public List<AlunoEntity> buscarTodos() {
        return alunoRepository.findByAtivoTrue();
    }

    public Optional<AlunoEntity> buscarPorID(Long id) {
        return alunoRepository.findById(id);
    }

    public AlunoEntity atualizarAluno(Long id, AlunoDTO alunoDTO) {
        Optional<AlunoEntity> aluno = alunoRepository.findById(id);
        if(aluno.isEmpty()){
            return null;
        }

        AlunoEntity alunoEntity = aluno.get();
        alunoEntity.putMetodo(alunoDTO);
        alunoRepository.save(alunoEntity);
        return  alunoEntity;
    }

    public boolean ativarAluno(Long id) {
        Optional<AlunoEntity> aluno = alunoRepository.findById(id);
        if(aluno.isEmpty()){
            return false;
        }

        AlunoEntity alunoEntity = aluno.get();
        alunoEntity.ativarAluno();
        alunoRepository.save(alunoEntity);
        return true;
    }

    public void deletarAluno(Long id) {
        Optional<AlunoEntity> aluno = alunoRepository.findById(id);
        if(aluno.isEmpty()){
            return;
        }

        for(TurmaEntity turma : aluno.get().getTurmas()){
            turma.getAlunos().remove(aluno.get());
            turmaRepository.save(turma);
        }

        aluno.get().getTurmas().clear();
        alunoRepository.delete(aluno.get());
    }


    public boolean inativarAluno(Long id) {
        Optional<AlunoEntity> aluno = alunoRepository.findById(id);
        if(aluno.isEmpty()){
            return false;
        }

        for(TurmaEntity turma : aluno.get().getTurmas()){
            turma.getAlunos().remove(aluno.get());
            turmaRepository.save(turma);
        }

        aluno.get().getTurmas().clear();
        AlunoEntity alunoEntity = aluno.get();
        alunoEntity.inativarAluno();
        alunoRepository.save(alunoEntity);
        return true;
    }
}
