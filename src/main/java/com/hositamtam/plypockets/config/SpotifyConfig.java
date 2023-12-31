package com.hositamtam.plypockets.config;

import java.io.IOException;
import java.time.Instant;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class SpotifyConfig {
    private static final String CLIENT_ID = "9aa067b28e444056ab123c76058ca7ab";
    private static final String CLIENT_SECRET = "28da9a874ab248ecae461398a4a20d53";
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(CLIENT_ID).setClientSecret(CLIENT_SECRET).build();

    private static String accessToken;
    private static Instant accessTokenExpiration;

//    public static String accessToken() {
//        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
//        try {
//            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
//            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
//            return spotifyApi.getAccessToken();
//        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
//            System.out.println("Error: " + e.getMessage());
//            return "error";
//        }
//    }

    public static String getAccessToken(){
        if (accessToken == null || Instant.now().isAfter(accessTokenExpiration)) {
            refreshAccessToken();
        }
        return accessToken;
    }

    private static void refreshAccessToken() {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            accessToken = clientCredentials.getAccessToken();
            // 토큰의 유효 시간을 계산하여 저장합니다.
            accessTokenExpiration = Instant.now().plusSeconds(clientCredentials.getExpiresIn());
            spotifyApi.setAccessToken(accessToken);
        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
            accessToken = "error";
        }
    }
}
