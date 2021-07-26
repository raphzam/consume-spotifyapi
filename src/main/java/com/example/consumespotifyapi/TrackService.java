package com.example.consumespotifyapi;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class TrackService {

//    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
//            .setAccessToken(accessToken)
//            .build();

    private final SpotifyApi spotifyApi;

    @Autowired
    public TrackService(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public Track getTrack(String trackId) {
        GetTrackRequest getTrackRequest = spotifyApi
                .getTrack(trackId)
                .market(CountryCode.US)
                .build();
        try {
            Track track = getTrackRequest.execute();
            return track;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Track> getTracks(String query) {
        SearchTracksRequest searchTracksRequest = spotifyApi
                .searchTracks(query)
                .market(CountryCode.US)
                .build();

        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();
            System.out.println("Total: " + trackPaging.getTotal());

            Track[] array = trackPaging.getItems();
            System.out.println(array[0].getExternalUrls());
            return Arrays.asList(trackPaging.getItems());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        throw new IllegalArgumentException("Some type of error cuzzzz");
    }

}
