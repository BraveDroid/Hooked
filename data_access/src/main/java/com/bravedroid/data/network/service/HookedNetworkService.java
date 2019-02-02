package com.bravedroid.data.network.service;

import com.bravedroid.data.network.model.StoryDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface HookedNetworkService {
    String BASE_URL = "http://take-home-test.hooked.media:8080/";

    @GET("story/{storyId}")
    Call<StoryDto> getStoryDto(@Header("Authorization") String authorization64Encoded,
                               @Path("storyId") String storyId);
}

