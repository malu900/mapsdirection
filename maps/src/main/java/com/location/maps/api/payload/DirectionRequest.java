package com.location.maps.api.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DirectionRequest {
    @NotEmpty
    @Size(max = 50)
    private String name;

    private List<WaypointRequest> waypoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WaypointRequest> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<WaypointRequest> waypoints) {
        this.waypoints = waypoints;
    }
}
