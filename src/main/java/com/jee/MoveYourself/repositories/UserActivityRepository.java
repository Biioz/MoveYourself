package com.jee.MoveYourself.repositories;

import com.jee.MoveYourself.entities.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserActivity ua WHERE ua.user.id = :userId AND ua.activity.id = :activityId")
    void deleteByUserIdAndActivityId(@Param("userId") Long userId, @Param("activityId") Long activityId);
}