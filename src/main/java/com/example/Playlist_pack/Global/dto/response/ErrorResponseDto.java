package com.example.Playlist_pack.Global.dto.response;

import org.springframework.http.HttpStatus;

public record ErrorResponseDto(int status, HttpStatus errorCode, String message) {


    public static ErrorResponseDto from(HttpStatus exceptionCode, String errorMessage) {
        return new ErrorResponseDto(exceptionCode.value(), exceptionCode, errorMessage);
    }
}