package com.bravedroid.data.encode;

import androidx.test.runner.AndroidJUnit4;
import com.bravedroid.data.util.Encoder64;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class EncoderTest {
    private Encoder64 SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new Encoder64();
    }

    @Test
    public void testEncodeTo64() {
        String input = "test:VF62pqDX";
        String result = SUT.encodeTo64(input);
        String expectedResult = "dGVzdDpWRjYycHFEWA==";
        assertEquals(expectedResult, result);
    }



}
