package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.request.VoteRequestDto;
import com.example.Playlist_pack.Dto.response.VoteLogResponseDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.VoteLogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("votes")
@CrossOrigin(origins = "*")
public class VoteLogController {

    private final VoteLogService voteLogService;

    @GetMapping("/{contentId}/{userId}")
    @Operation(summary = "컨텐츠 조회시 사용자 투표 여부 확인", description = "사용자가 해당 컨텐츠를 투표하였다면 투표 결과를, 안했다면 안했다는 결과를 반환합니다.")
    public HttpResponse<VoteLogResponseDto> getVoteDetils(@PathVariable Long contentId, @PathVariable Long userId){
        return HttpResponse.okBuild(voteLogService.getVoteDetils(contentId, userId));
    }

    @PostMapping
    @Operation(summary = "투표하기", description = "사용자의 투표를 반영하고 투표 결과를 반환한다. (이미 투표한 컨텐츠에 대해서 다시 요청했다면 Error 처리)")
    public HttpResponse<VoteLogResponseDto> submitVote(@Validated @RequestBody VoteRequestDto voteRequestDto){
        return HttpResponse.createdBuild(voteLogService.submitVote(voteRequestDto));
    }
}