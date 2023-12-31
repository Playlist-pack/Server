package com.hositamtam.plypockets.dto.request;


import com.hositamtam.plypockets.domain.User;
import com.hositamtam.plypockets.domain.Vote;
import com.hositamtam.plypockets.domain.VoteLog;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record VoteRequestDto(
        @NotNull
        @Schema(description = "컨텐츠 아이디", nullable = false)
        Long contentId,
        
        @Schema(description = "사용자 아이디", nullable = true)
        Long userId,
        @NotNull
        @Schema(description = "투표 선택지 번호", nullable = false)
        Integer choice) {

    public VoteLog toEntity(Vote vote, User user) {
        return VoteLog.builder()
                .vote(vote)
                .user(user)
                .build();
    }
}
