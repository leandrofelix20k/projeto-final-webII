package com.projeto02_web2.Crud.repository;

import com.projeto02_web2.Crud.model.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    List<AlunoEntity> findByAtivoTrue();
}
