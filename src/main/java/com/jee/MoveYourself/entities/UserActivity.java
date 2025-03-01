package com.jee.MoveYourself.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @EmbeddedId
    private UserActivityId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("activityId")
    @JoinColumn(name = "activity_id")
    private Activity activity;

    // Constructors, getters, and setters
    public UserActivity() {
    }

    public UserActivity(User user, Activity activity) {
        this.user = user;
        this.activity = activity;
        this.id = new UserActivityId(user.getId(), activity.getId());
    }

    public UserActivityId getId() {
        return id;
    }

    public void setId(UserActivityId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}