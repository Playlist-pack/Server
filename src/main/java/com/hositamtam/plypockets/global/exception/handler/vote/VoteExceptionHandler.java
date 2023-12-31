package com.hositamtam.plypockets.global.exception.handler.vote;

import com.hositamtam.plypockets.global.dto.response.ErrorResponseDto;
import com.hositamtam.plypockets.global.dto.response.HttpResponse;
import com.hositamtam.plypockets.global.exception.custom.vote.UserAlreadyVotedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VoteExceptionHandler {
    @ExceptionHandler(UserAlreadyVotedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse<ErrorResponseDto> UserAlreadyVotedExceptionHandler(UserAlreadyVotedException e) {
        log.warn("UserAlreadyVotedException : {}", e);
        return HttpResponse.status(e.getHttpStatus())
                .body(ErrorResponseDto.from(e.getHttpStatus(), e.getMessage()));
    }
}
