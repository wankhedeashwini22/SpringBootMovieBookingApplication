package com.ultraPlexMovieBookingSystem.repository;

import com.ultraPlexMovieBookingSystem.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
