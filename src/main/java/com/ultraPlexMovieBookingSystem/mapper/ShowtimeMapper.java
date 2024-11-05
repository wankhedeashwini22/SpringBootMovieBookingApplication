package com.ultraPlexMovieBookingSystem.mapper;

import com.ultraPlexMovieBookingSystem.dto.ShowtimeDto;
import com.ultraPlexMovieBookingSystem.entity.Movie;
import com.ultraPlexMovieBookingSystem.entity.Screen;
import com.ultraPlexMovieBookingSystem.entity.Showtime;

public class ShowtimeMapper {
    public static ShowtimeDto toShowtimeDto(Showtime showtime) {
        return ShowtimeDto.builder().showtimeId(showtime.getShowtimeId())
        .movieId(showtime.getMovie().getMovieId())
                .screenId(showtime.getScreen().getScreenId())
                .startTime(showtime.getStartTime())
                .endTime(showtime.getEndTime())
                .currentDateTime(showtime.getDateTime()).build();
    }

    public static Showtime toShowtimeEntity(ShowtimeDto showtimeDto, Movie movie, Screen screen) {
        return Showtime.builder()
                .showtimeId(showtimeDto.getShowtimeId())
                .movie(movie)
                .screen(screen)
                .startTime(showtimeDto.getStartTime())
                .endTime(showtimeDto.getEndTime())
                .dateTime(showtimeDto.getCurrentDateTime())
                .build();
    }
}
