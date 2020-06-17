package com.location.maps.respository;

import com.location.maps.model.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    Optional<Direction> findById(Long directionId);
    List<Direction> findByIdIn(List<Long> directionIds);
    List<Direction> findByIdIn(List<Long> directionsId, Sort sort);
    @Query("SELECT d FROM Direction d where d.user.username =  :userName")
    List<Direction> getDirectionsByCreatedUser(@Param("userName") String userName);
    @Query("SELECT u.directions FROM User u WHERE u.username = :userName")
    Set<Direction> getFavoriteDirections(@Param("userName") String userName);
//    @Query("select d FROM Direction d where d.users.)
//    List<Direction>  getOneWithPosts(@Param("id") Long id);
//    @Query("SELECT d FROM Direction d where d.users.username =  :userName")
//    List<Direction> getDirectionsByCreatedUser(@Param("userName") String userName);
}
