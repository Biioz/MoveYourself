package com.jee.MoveYourself.repositories;

import com.jee.MoveYourself.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    // Find programs by user ID
    List<Program> findByUserId(Long userId);

    // Find programs by name (case-insensitive search)
    List<Program> findByNameContainingIgnoreCase(String name);
}
