package com.jee.MoveYourself.controller;

import com.jee.MoveYourself.entities.Program;
import com.jee.MoveYourself.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    // Create a new program
    @PostMapping
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        Program newProgram = programService.createProgram(program);
        return ResponseEntity.ok(newProgram);
    }

    // Get a program by ID
    @GetMapping("/{id}")
    public ResponseEntity<Program> getProgramById(@PathVariable Long id) {
        Optional<Program> program = programService.findProgramById(id);
        return program.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get programs by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Program>> getProgramsByUserId(@PathVariable Long userId) {
        List<Program> programs = programService.findProgramsByUserId(userId);
        return ResponseEntity.ok(programs);
    }

    // Search programs by name
    @GetMapping("/search")
    public ResponseEntity<List<Program>> searchProgramsByName(@RequestParam String name) {
        List<Program> programs = programService.searchProgramsByName(name);
        return ResponseEntity.ok(programs);
    }

    // Update a program
    @PutMapping("/{id}")
    public ResponseEntity<Program> updateProgram(@PathVariable Long id, @RequestBody Program program) {
        program.setId(id); // Ensure the ID is set
        Program updatedProgram = programService.updateProgram(program);
        return ResponseEntity.ok(updatedProgram);
    }

    // Delete a program by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }

    // Get all programs
    @GetMapping
    public ResponseEntity<List<Program>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }
}