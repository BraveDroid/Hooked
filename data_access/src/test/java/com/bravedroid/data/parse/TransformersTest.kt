package com.bravedroid.data.parse

import com.bravedroid.data.network.model.StoryDto
import com.bravedroid.data.util.transformToStory
import com.bravedroid.domain.model.Story
import com.google.gson.Gson
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths


class TransformersTest {
    private lateinit var storyDto: StoryDto
    val gson = Gson()

    @Before
    fun setUp() {
        val resource = javaClass.classLoader.getResource("stories.json")
        val jsonString = String(Files.readAllBytes(Paths.get(resource!!.toURI())), Charset.defaultCharset())
        storyDto = gson.fromJson(jsonString, StoryDto::class.java)
    }

    @Test
    fun testToStory() {
        val story = transformToStory(storyDto)
        val expectedStory = Story(
            "scavengerhunt-app",
            "Scavenger Hunt",
            "At first I thought this unknown number was a total creeper, but then something sort of magical happened.",
            "https://hooked-parse-server.s3.amazonaws.com/72c59901f54eb0ad95d8f5312d7503d4_152.jpg"
        )
        assertThat(expectedStory, `is`(equalTo(story)))
    }
}
