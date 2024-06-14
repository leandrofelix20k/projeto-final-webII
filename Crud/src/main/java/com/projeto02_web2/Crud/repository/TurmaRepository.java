package com.projeto02_web2.Crud.repository;

import com.projeto02_web2.Crud.model.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaEntity, Long>{
    List<TurmaEntity> findAllByAtivoTrue();
}
