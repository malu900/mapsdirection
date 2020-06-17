//package com.location.maps.model;
//
//import org.springframework.data.annotation.CreatedBy;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//
//@Entity
//@Table(name = "user_directions", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
//                "direction_id",
//                "user_id"
//        })
//})
//public class UserDirections {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "direction_id")
//    private Direction direction;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @NotEmpty
//    private boolean createdOrsaved;
//
//    @CreatedBy
//    private Long createdBy;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Direction getDirection() {
//        return direction;
//    }
//
//    public void setDirection(Direction direction) {
//        this.direction = direction;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Long getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(Long createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public boolean isCreatedOrsaved() {
//        return createdOrsaved;
//    }
//
//    public void setCreatedOrsaved(boolean createdOrsaved) {
//        this.createdOrsaved = createdOrsaved;
//    }
//}
