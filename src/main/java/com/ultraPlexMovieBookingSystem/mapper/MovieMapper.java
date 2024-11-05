package com.ultraPlexMovieBookingSystem.mapper;

import com.ultraPlexMovieBookingSystem.dto.MovieDto;
import com.ultraPlexMovieBookingSystem.entity.Movie;

public class MovieMapper {

        public static MovieDto toMovieDto(Movie movie) {
            return MovieDto.builder().movieId(movie.getMovieId())
                    .title(movie.getTitle())
                    .duration(movie.getDuration())
                    .rating(movie.getRating())
                    .releaseDate(movie.getReleaseDate())
                    .build();
        }

        public static Movie toMovieEntity(MovieDto movieDto) {
            return Movie.builder().movieId(movieDto.getMovieId())
                    .title(movieDto.getTitle())
                    .duration(movieDto.getDuration())
                    .rating(movieDto.getRating())
                    .releaseDate(movieDto.getReleaseDate())
                    .build();
        }
}
