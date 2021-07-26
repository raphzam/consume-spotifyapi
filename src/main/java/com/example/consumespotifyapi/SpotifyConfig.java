package com.example.consumespotifyapi;

import com.wrapper.spotify.SpotifyApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyConfig {

    @Value("${spotify.bearer.token}")
    private String accessToken;

    @Bean
    public SpotifyApi spotifyApi() {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
        return spotifyApi;
    }

}
