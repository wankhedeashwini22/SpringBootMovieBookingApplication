package com.ultraPlexMovieBookingSystem.mapper;

import com.ultraPlexMovieBookingSystem.dto.SeatDto;
import com.ultraPlexMovieBookingSystem.entity.Screen;
import com.ultraPlexMovieBookingSystem.entity.Seat;

public class SeatMapper {
    public static SeatDto toSeatDto(Seat seat) {
        return SeatDto.builder().seatId(seat.getSeatId())
        .seatNumber(seat.getSeatNumber())
        .isAvailable(seat.isAvailable())
        .screenId(seat.getScreen().getScreenId())
        .build();

    }

    public static Seat toSeatEntity(SeatDto dto, Screen screen) {
        return Seat.builder().seatId(dto.getSeatId())
        .seatNumber(dto.getSeatNumber())
        .isAvailable(dto.isAvailable())
        .screen(screen)
        .build();
    }
}
