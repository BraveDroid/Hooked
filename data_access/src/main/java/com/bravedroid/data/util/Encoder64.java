package com.bravedroid.data.util;

import android.annotation.TargetApi;
import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encoder64 {
    public static String encodeTo64(String input) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return useOpenJdk(input);
        }
        return useAndroid(input);
    }

    private static String useAndroid(String input) {
        return android.util.Base64.encodeToString(input.getBytes(), android.util.Base64.NO_WRAP | android.util.Base64.URL_SAFE);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private static String useOpenJdk(String input) {
        byte[] message = input.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(message);
    }
}
