package com.hositamtam.plypockets.controller;

import com.hositamtam.plypockets.dto.SpotifySearchResponseDto;
import com.hositamtam.plypockets.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class SpotifyController {
    private final SpotifyService spotifyService;

    @GetMapping("/search/{trackname}")
    public List<SpotifySearchResponseDto> searchTracksByTrackname(@PathVariable String trackname) throws IOException, ParseException, SpotifyWebApiException {
        return spotifyService.SearchByTrackname(trackname);
    }


    @GetMapping("/search/{artist}/{trackname}")
    public List<SpotifySearchResponseDto> searchTracksByTracknameAndArtist(@PathVariable String trackname,@PathVariable String artist) throws IOException, ParseException, SpotifyWebApiException {


        return spotifyService.SearchByTracknameAndArtist(trackname,artist);
    }

    @GetMapping("/search/spotifyId/{spotifyId}")
    public SpotifySearchResponseDto searchTracksBySpotifyId(@PathVariable String spotifyId) throws IOException, ParseException, SpotifyWebApiException {
        return spotifyService.getTrackBySpotifyId(spotifyId);
    }

    @GetMapping("/search/genre/{genre}")
    public List<SpotifySearchResponseDto> searchTracksByGenre(@PathVariable String genre)
            throws IOException, SpotifyWebApiException {

        return spotifyService.searchByGenre(genre);
    }
    @GetMapping("/TodayHot100")
    public List<SpotifySearchResponseDto> getHot100Chart()
            throws IOException, SpotifyWebApiException {
        return spotifyService.getHot100Chart();
    }

    @GetMapping("/KoreanHot100")
    public List<SpotifySearchResponseDto> getKoreanHot100chart()
            throws IOException, SpotifyWebApiException {
        return spotifyService.getKoreanHot100Chart();
    }


}
