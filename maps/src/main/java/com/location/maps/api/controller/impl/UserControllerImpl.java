package com.location.maps.api.controller.impl;

import com.location.maps.api.controller.UserController;
import com.location.maps.api.payload.DirectionResponse;
import com.location.maps.api.payload.UserProfile;
import com.location.maps.api.payload.UserSummary;
import com.location.maps.api.payload.WaypointResponse;
import com.location.maps.api.payload.response.ApiResponse;
import com.location.maps.exception.AppException;
import com.location.maps.exception.ResourceNotFoundException;
import com.location.maps.model.*;
import com.location.maps.respository.DirectionRepository;
import com.location.maps.respository.UserRepository;
import com.location.maps.security.CurrentUser;
import com.location.maps.security.UserPrincipal;
import com.location.maps.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserControllerImpl implements UserController {

    private final UserRepository userRepository;
    private final DirectionService directionService;
    private final DirectionRepository directionRepository;
    private static final Logger logger = LoggerFactory.getLogger(com.location.maps.api.controller.UserController.class);

    @Autowired
    public UserControllerImpl(UserRepository userRepository, DirectionService directionService, DirectionRepository directionRepository) {
        this.userRepository = userRepository;
        this.directionService = directionService;
        this.directionRepository = directionRepository;
    }

    @Override
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @Override
    @CrossOrigin(origins = "http://localhost:3000")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());

        return userProfile;
    }

    @Override
    public ResponseEntity<List<DirectionResponse>> getCreatedDirections(@PathVariable(value = "username") String username) {
        List<DirectionResponse> directionResponses = directionService.getDirectionsCreatedBy(username);
        return new ResponseEntity<>(directionResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DirectionResponse>> getFavoriteDirections(@PathVariable(value = "username") String username) {
        List<DirectionResponse> directionResponses = directionService.getFavoriteDirectionsByUser(username);
        return new ResponseEntity<>(directionResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createFavoriteDirections(@CurrentUser UserPrincipal userPrincipal, @PathVariable(value="username") String username, @PathVariable(value="id") Long id){

        Optional<Direction> direction = directionRepository.findById(id);
        if(direction == null){
            throw new AppException("Username not found");
        }
        Optional<User> user = userRepository.findByUsername(userPrincipal.getUsername());
        if(user == null){
            throw new AppException("Username not found");
        }

        User usermodel = user.get();
        Direction directionmodel = direction.get();

        usermodel.getDirections().add(directionmodel);

        userRepository.save(usermodel);
        return new ResponseEntity<>(new ApiResponse(true, "User registered successfully"), HttpStatus.OK);
    }

}