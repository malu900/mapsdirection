package com.location.maps.api.payload;


import java.util.List;

public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Long directionCount;
    private List<DirectionResponse> directionResponses;

    public UserProfile(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }

    public UserProfile(Long id, String username, String name, Long directionCount) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.setDirectionCount(directionCount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public Long getDirectionCount() {
        return directionCount;
    }

    public void setDirectionCount(Long directionCount) {
        this.directionCount = directionCount;
    }

    public List<DirectionResponse> getDirectionResponses() {
        return directionResponses;
    }

    public void setDirectionResponses(List<DirectionResponse> directionResponses) {
        this.directionResponses = directionResponses;
    }
}
