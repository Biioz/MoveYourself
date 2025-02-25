package com.jee.MoveYourself.services;

import com.jee.MoveYourself.entities.Program;
import com.jee.MoveYourself.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    // Create a new program
    public Program createProgram(Program program) {
        return programRepository.save(program);
    }

    // Find a program by ID
    public Optional<Program> findProgramById(Long id) {
        return programRepository.findById(id);
    }

    // Find programs by user ID
    public List<Program> findProgramsByUserId(Long userId) {
        return programRepository.findByUserId(userId);
    }

    // Search programs by name (case-insensitive)
    public List<Program> searchProgramsByName(String name) {
        return programRepository.findByNameContainingIgnoreCase(name);
    }

    // Update a program
    public Program updateProgram(Program program) {
        return programRepository.save(program);
    }

    // Delete a program by ID
    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }

    // List all programs
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }
}