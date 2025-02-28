package com.jee.MoveYourself.repositories;

import com.jee.MoveYourself.entities.Pathology;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PathologyRepository extends JpaRepository<Pathology, Long> {
    List<Pathology> findAll();
}