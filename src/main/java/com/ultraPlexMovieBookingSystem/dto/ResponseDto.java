package com.ultraPlexMovieBookingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto {

    private String statusCode;
    private String statusMessage;
    private String response;

}
