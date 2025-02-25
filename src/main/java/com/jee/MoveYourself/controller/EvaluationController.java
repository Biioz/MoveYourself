package com.jee.MoveYourself.controller;

import com.jee.MoveYourself.entities.Evaluation;
import com.jee.MoveYourself.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    // Add a new evaluation
    @PostMapping
    public ResponseEntity<Evaluation> addEvaluation(@RequestBody Evaluation evaluation) {
        Evaluation newEvaluation = evaluationService.addEvaluation(evaluation);
        return ResponseEntity.ok(newEvaluation);
    }

    // Get an evaluation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        Optional<Evaluation> evaluation = evaluationService.findEvaluationById(id);
        return evaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get evaluations by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByUserId(@PathVariable Long userId) {
        List<Evaluation> evaluations = evaluationService.findEvaluationsByUserId(userId);
        return ResponseEntity.ok(evaluations);
    }

    // Get evaluations by activity ID
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByActivityId(@PathVariable Long activityId) {
        List<Evaluation> evaluations = evaluationService.findEvaluationsByActivityId(activityId);
        return ResponseEntity.ok(evaluations);
    }

    // Get evaluations by user ID and activity ID
    @GetMapping("/user/{userId}/activity/{activityId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByUserAndActivity(
            @PathVariable Long userId, @PathVariable Long activityId) {
        List<Evaluation> evaluations = evaluationService.findEvaluationsByUserAndActivity(userId, activityId);
        return ResponseEntity.ok(evaluations);
    }

    // Update an evaluation
    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id, @RequestBody Evaluation evaluation) {
        evaluation.setId(id); // Ensure the ID is set
        Evaluation updatedEvaluation = evaluationService.updateEvaluation(evaluation);
        return ResponseEntity.ok(updatedEvaluation);
    }

    // Delete an evaluation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }

    // Get all evaluations
    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationService.getAllEvaluations();
        return ResponseEntity.ok(evaluations);
    }
}