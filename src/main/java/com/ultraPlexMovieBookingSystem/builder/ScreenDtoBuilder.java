package com.ultraPlexMovieBookingSystem.builder;


import com.ultraPlexMovieBookingSystem.dto.ScreenDto;
import com.ultraPlexMovieBookingSystem.request.ScreenRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScreenDtoBuilder {

    public static ScreenDto buildScreenDto(ScreenRequest screenRequest) {
        return ScreenDto.builder().screenId(screenRequest.getScreenId())
                .screenNumber(screenRequest.getScreenNumber()).cinemaId(screenRequest.getCinemaId())
                .movieId(screenRequest.getMovieId()).build();
    }

}
