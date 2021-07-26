package com.example.consumespotifyapi;

import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TrackController {

    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public Object getTrack() {
        Track track = trackService.getTrack("2fXwCWkh6YG5zU1IyvQrbs");
        System.out.println(track.getExternalUrls());
        return track;
    }

    @GetMapping ("/search")
    public List<Track> searchTracks(@RequestParam String q) {
        List<Track> searchResults = trackService.getTracks(q);
        return searchResults;
    }

}
