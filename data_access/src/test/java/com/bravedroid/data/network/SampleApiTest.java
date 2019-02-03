package com.bravedroid.data.network;

import com.bravedroid.data.network.model.StoryDto;
import com.bravedroid.data.network.service.HookedNetworkService;
import com.bravedroid.data.network.service.HookedNetworkServiceFactory;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SampleApiTest {
    private HookedNetworkService SUT;
    private static final String STORY_ID = "scavengerhunt";
    private static final String AUTH_BASIC = "Basic dGVzdDpWRjYycHFEWA==";


    @Before
    public void setUp() {
        HookedNetworkServiceFactory factory = new HookedNetworkServiceFactory();
        SUT = factory.create();
    }

    @Test
    public void getStoryDto_isCorrect() throws IOException {
        Response<StoryDto> response = SUT.getStoryDto(AUTH_BASIC,STORY_ID).execute();
        int code = response.code();
        assertThat(code, is(equalTo(HTTP_OK)));
    }
}
