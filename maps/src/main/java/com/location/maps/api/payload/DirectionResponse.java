package com.location.maps.api.payload;

import java.util.Date;
import java.util.List;

public class DirectionResponse {
    private Long id;
    private String name;
    private List<WaypointResponse> waypoints;
    private UserSummary createdBy;
    private Date creationDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WaypointResponse> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<WaypointResponse> waypoints) {
        this.waypoints = waypoints;
    }

    public UserSummary getCreatedBy() {
        return createdBy;
    }

    public void setUserCreatedBy(UserSummary createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
