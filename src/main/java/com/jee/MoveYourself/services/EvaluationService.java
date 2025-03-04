/*package com.jee.MoveYourself.services;

import com.jee.MoveYourself.entities.Evaluation;
import com.jee.MoveYourself.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    // Add a new evaluation
    public Evaluation addEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    // Find an evaluation by ID
    public Optional<Evaluation> findEvaluationById(Long id) {
        return evaluationRepository.findById(id);
    }

    // Find evaluations by user ID
    public List<Evaluation> findEvaluationsByUserId(Long userId) {
        return evaluationRepository.findByUserId(userId);
    }

    // Find evaluations by activity ID
    public List<Evaluation> findEvaluationsByActivityId(Long activityId) {
        return evaluationRepository.findByActivityId(activityId);
    }

    // Find evaluations by user ID and activity ID
    public List<Evaluation> findEvaluationsByUserAndActivity(Long userId, Long activityId) {
        return evaluationRepository.findByUserIdAndActivityId(userId, activityId);
    }

    // Update an evaluation
    public Evaluation updateEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    // Delete an evaluation by ID
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }

    // List all evaluations
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }
}*/