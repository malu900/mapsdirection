package com.location.maps.api.controller;

import com.location.maps.api.payload.DirectionResponse;
import com.location.maps.api.payload.UserProfile;
import com.location.maps.api.payload.UserSummary;
import com.location.maps.model.Direction;
import com.location.maps.model.User;
import com.location.maps.security.CurrentUser;
import com.location.maps.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
public interface UserController {


    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser);
//    @CrossOrigin
    @GetMapping("/users/{username}")
    UserProfile getUserProfile(@PathVariable(value = "username") String username);

    @GetMapping("/users/{username}/directions")
    ResponseEntity<List<DirectionResponse>> getCreatedDirections(@PathVariable(value = "username") String username);
//
    @GetMapping("/users/{username}/directions/favorites/")
    ResponseEntity<List<DirectionResponse>> getFavoriteDirections(@PathVariable(value = "username") String username);

    @GetMapping(value ="/users/{username}/directions/{id}")
    ResponseEntity<?> createFavoriteDirections(@CurrentUser UserPrincipal userPrincipal, @PathVariable(value="username") String username, @PathVariable(value="id") Long id);
}
