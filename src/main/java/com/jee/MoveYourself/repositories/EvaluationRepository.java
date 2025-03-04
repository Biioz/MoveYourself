package com.jee.MoveYourself.repositories;

import com.jee.MoveYourself.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    // Find evaluations by user ID
    List<Evaluation> findByUserId(Long userId);

    // Find evaluations by activity ID
    List<Evaluation> findByActivityId(Long activityId);

    // Find evaluations by user ID and activity ID
    List<Evaluation> findByUserIdAndActivityId(Long userId, Long activityId);
}