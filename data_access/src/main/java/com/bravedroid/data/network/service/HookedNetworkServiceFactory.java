package com.bravedroid.data.network.service;

import org.jetbrains.annotations.NotNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HookedNetworkServiceFactory {
    private static Retrofit retrofit;

    public HookedNetworkService create() {
        return getRetrofitInstance().create(HookedNetworkService.class);
    }

    @NotNull
    private synchronized Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(HookedNetworkService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
