package com.location.maps.mapper;

import com.location.maps.api.payload.DirectionResponse;
import com.location.maps.api.payload.UserSummary;
import com.location.maps.api.payload.WaypointResponse;
import com.location.maps.model.Direction;
import com.location.maps.model.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static DirectionResponse mapPollToPollResponse(Direction direction, Map<Long, Long> WaypointDirectionMap, User creator) {
        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setId(direction.getId());
        directionResponse.setName(direction.getName());

//        List<WaypointResponse> waypointResponses = direction.getWaypoints().stream().map(waypoint -> {
//            WaypointResponse waypointResponse = new WaypointResponse();
//            waypointResponse.setId(waypoint.getId());
//            waypointResponse.setName(waypoint.getName());
//            waypointResponse.setLatitude(waypoint.getLatitude());
//            waypointResponse.setLongitude(waypoint.getLongitude());
//
//            return waypointResponse;
//        }).collect(Collectors.toList());

        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        directionResponse.setUserCreatedBy(creatorSummary);

        return directionResponse;
    }

    public Object map(Direction direction, Class<DirectionResponse> directionResponseClass) {
        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setId(direction.getId());
        directionResponse.setName(direction.getName());

//        List<WaypointResponse> waypointResponses = direction.getWaypoints().stream().map(waypoint -> {
//            WaypointResponse waypointResponse = new WaypointResponse();
//            waypointResponse.setId(waypoint.getId());
//            waypointResponse.setName(waypoint.getName());
//            waypointResponse.setLatitude(waypoint.getLatitude());
//            waypointResponse.setLongitude(waypoint.getLongitude());
//
//            return waypointResponse;
//        }).collect(Collectors.toList());

//        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
//        directionResponse.setUserCreatedBy(creatorSummary);

        return directionResponse;
    }
}
