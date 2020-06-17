package com.location.maps.service;

import com.location.maps.api.payload.*;
import com.location.maps.mapper.ModelMapper;
import com.location.maps.model.Direction;
import com.location.maps.model.User;
import com.location.maps.model.Waypoint;
import com.location.maps.respository.DirectionRepository;
import com.location.maps.respository.UserRepository;
import com.location.maps.respository.WaypointRepository;
import com.location.maps.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DirectionService {

    private final DirectionRepository directionRepository;
    private final WaypointRepository waypointRepository;
    private final UserRepository userRepository;

    @Autowired
    public DirectionService(DirectionRepository directionRepository, WaypointRepository waypointRepository, UserRepository userRepository) {
        this.directionRepository = directionRepository;
        this.waypointRepository = waypointRepository;
        this.userRepository = userRepository;
    }

    public Direction createDirection(UserPrincipal creator, DirectionRequest directionRequest) {
        Direction direction = new Direction();
        direction.setName(directionRequest.getName());

        directionRequest.getWaypoints().forEach(waypointRequest -> {
            direction.addWaypoint(new Waypoint(waypointRequest.getName(), waypointRequest.getLatitude(), waypointRequest.getLongitude()));
        });
        Optional<User> user = userRepository.findById(creator.getId());
        User usermodel = user.get();
        direction.setUser(usermodel);


        return directionRepository.save(direction);
    }

    public List<DirectionResponse> getAllDirections() {
        List<Direction> directions = directionRepository.findAll();
        List<DirectionResponse> directionResponses = getDirectionResponses(directions);

//        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());

        return directionResponses;
    }

    private List<DirectionResponse> getDirectionResponses(List<Direction> directions) {
        return directions.stream().map(direction -> {
            DirectionResponse directionResponse = new DirectionResponse();
            directionResponse.setId(direction.getId());
            directionResponse.setName(direction.getName());
            if (direction.getUser().getId() != null) {
                directionResponse.setUserCreatedBy(new UserSummary(direction.getUser().getId(), direction.getUser().getName(), direction.getUser().getUsername()));
            }
            List<Waypoint> waypoints = direction.getWaypoints();
            List<WaypointResponse> waypointResponses = waypoints.stream().map(waypoint -> {
                WaypointResponse waypointResponse = new WaypointResponse();
                waypointResponse.setId(waypoint.getId());
                waypointResponse.setName(waypoint.getName());
                waypointResponse.setLatitude(waypoint.getLatitude());
                waypointResponse.setLongitude(waypoint.getLongitude());
                return waypointResponse;
            }).collect(Collectors.toList());

            directionResponse.setWaypoints(waypointResponses);
            directionResponse.setCreationDateTime(direction.getCreatedAt());
            return directionResponse;
        }).collect(Collectors.toList());
    }

    public List<DirectionResponse> getDirectionsCreatedBy(String username) {
        List<Direction> directions = directionRepository.getDirectionsByCreatedUser(username);
        return getDirectionResponses(directions);
    }

    public List<DirectionResponse> getFavoriteDirectionsByUser(String username) {
        Set<Direction> directionsSet = directionRepository.getFavoriteDirections(username);
        List<Direction> directions = new ArrayList<>(directionsSet);
        List<DirectionResponse> directionResponses = getDirectionResponses(directions);
//        List<DirectionResponse> directionResponses1 = directionResponses.stream().map(direction -> {
//            DirectionResponse directionResponse = new DirectionResponse();
//            directionResponse.setId(direction.getId());
//            directionResponse.setName(direction.getName());
//            if (direction.getUser().getId() != null) {
//                directionResponse.setUserCreatedBy(new UserSummary(direction.getUser().getId(), direction.getUser().getName(), direction.getUser().getUsername()));
//            }
//            List<Waypoint> waypoints = direction.getWaypoints();
//            List<WaypointResponse> waypointResponses = waypoints.stream().map(waypoint -> {
//                WaypointResponse waypointResponse = new WaypointResponse();
//                waypointResponse.setId(waypoint.getId());
//                waypointResponse.setName(waypoint.getName());
//                waypointResponse.setLatitude(waypoint.getLatitude());
//                waypointResponse.setLongitude(waypoint.getLongitude());
//                return waypointResponse;
//            }).collect(Collectors.toList());
//
//            directionResponse.setWaypoints(waypointResponses);
//            directionResponse.setCreationDateTime(direction.getCreatedAt());
//            return directionResponse;
//        }).collect(Collectors.toList());

        return directionResponses;
    }

}
