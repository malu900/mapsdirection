package com.location.maps.api.controller;

import com.location.maps.api.payload.DirectionRequest;
import com.location.maps.api.payload.DirectionResponse;
import com.location.maps.api.payload.response.ApiResponse;
import com.location.maps.exception.AppException;
import com.location.maps.model.Direction;
import com.location.maps.model.User;
import com.location.maps.respository.DirectionRepository;
import com.location.maps.respository.UserRepository;
import com.location.maps.security.CurrentUser;
import com.location.maps.security.UserPrincipal;
import com.location.maps.service.DirectionService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/directions")
public class DirectionController {
    private final DirectionRepository directionRepository;

    private final UserRepository userRepository;

    private final DirectionService directionService;

    public DirectionController(DirectionRepository directionRepository, UserRepository userRepository, DirectionService directionService) {
        this.directionRepository = directionRepository;
        this.userRepository = userRepository;
        this.directionService = directionService;
    }

    @GetMapping
    public ResponseEntity<List<DirectionResponse>> getAllEmployees() {
        List<DirectionResponse> list = directionService.getAllDirections();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createDirection(@CurrentUser UserPrincipal currentUser, @RequestBody DirectionRequest directionRequest) {
        Direction direction = directionService.createDirection(currentUser, directionRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{directionId}")
                .buildAndExpand(direction.getId()).toUri();

        direction.setCreatedBy(currentUser.getId());
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "direction created!"));
    }
//    @GetMapping(value="/directions/{id}")
//    @PreAuthorize("hasRole('USER')")
//    // ResponseEntity represents the whole HTTP response: status code, headers, and body.
//    // Because of it, we can use it to fully configure the HTTP response.
//    public ResponseEntity<?> createFavoriteDirections(@CurrentUser UserPrincipal currentUser, @PathVariable Long id){
//
//        Optional<Direction> direction = directionRepository.findById(id);
//        if(direction == null){
//            throw new AppException("Username not found");
//        }
//        Optional<User> user = userRepository.findByUsername(currentUser.getUsername());
//        if(user == null){
//            throw new AppException("Username not found");
//        }
//
//        User usermodel = user.get();
//        Direction directionmodel = direction.get();
//
//        directionmodel.getUsers().add(usermodel);
//        usermodel.getDirections().add(directionmodel);
//
//        userRepository.save(usermodel);
//
////        //change custom path
////        URI location = ServletUriComponentsBuilder
////                .fromCurrentContextPath().path("/api/users/{username}/directions/favorites/{id}")
////                .buildAndExpand(usermodel.getUsername(), directionmodel.getId()).toUri();
////        return ResponseEntity.created(location).body(new ApiResponse(true, "User direction successfully"));
//        return new ResponseEntity<>(new ApiResponse(true, "User registered successfully"), HttpStatus.OK);
//    }
}
