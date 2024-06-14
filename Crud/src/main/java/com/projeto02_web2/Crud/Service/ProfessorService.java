package com.projeto02_web2.Crud.Service;

import com.projeto02_web2.Crud.DTO.ProfessorDTO;
import com.projeto02_web2.Crud.model.ProfessorEntity;
import com.projeto02_web2.Crud.model.TurmaEntity;
import com.projeto02_web2.Crud.repository.ProfessorRepository;
import com.projeto02_web2.Crud.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;

    public ProfessorEntity cadastrarProfessor(ProfessorDTO dto) {
        ProfessorEntity professor = new ProfessorEntity();
        BeanUtils.copyProperties(dto, professor);
        return professorRepository.save(professor);
    }

    public List<ProfessorEntity> buscarTodos() {
        return professorRepository.findByAtivoTrue();
    }

    public Optional<ProfessorEntity> buscarPorID(Long id) {
        return professorRepository.findById(id);
    }

    public ProfessorEntity atualizarProfessor(Long id, ProfessorDTO professorDTO) {
        Optional<ProfessorEntity> professor = professorRepository.findById(id);
        if(professor.isEmpty()){
            return null;
        }

        ProfessorEntity professorEntity = professor.get();
        professorEntity.putMetodo(professorDTO);
        professorRepository.save(professorEntity);
        return professorRepository.save(professorEntity);
    }

    public boolean ativarProfessor(Long id) {
        Optional<ProfessorEntity> professor = professorRepository.findById(id);
        if(professor.isEmpty()){
            return false;
        }

        ProfessorEntity professorEntity = professor.get();
        professorEntity.ativarProfessor();
        professorRepository.save(professorEntity);
        return true;
    }

    public void deletarProfessor(Long id) {
        Optional<ProfessorEntity> professor = professorRepository.findById(id);
        if(professor.isEmpty()){
            return;
        }

        for(TurmaEntity turma : professor.get().getTurmas()){
            turma.setProfessor(null);
            turmaRepository.save(turma);
        }

        professor.get().getTurmas().clear();
        professorRepository.delete(professor.get());
    }

    public boolean inativarProfessor(Long id) {
        Optional<ProfessorEntity> professor = professorRepository.findById(id);
        if(professor.isEmpty()){
            return false;
        }

        ProfessorEntity professorEntity = professor.get();
        professorEntity.inativarProfessor();
        professorRepository.save(professorEntity);
        return true;
    }
}

