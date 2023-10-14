package com.mayann.network

import com.google.gson.Gson
import com.mayann.domain.model.track.Track
import com.mayann.network.features.track.TrackRemoteSource
import com.mayann.network.features.track.TrackService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @use Basic unit testing to test response of API for searching tracks
 * @desc Mobile Developer
 * @since 1.0
 */
@RunWith(JUnit4::class)
class TrackServiceTest {
    private lateinit var trackRemoteSource: TrackRemoteSource
    private lateinit var trackService: TrackService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        trackService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(BuildConfig.BASE_API_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrackService::class.java)

        trackRemoteSource = TrackRemoteSource(trackService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `for search A star is born, api must return 3 tracks with http code 200`() = runTest {
        val tracks = listOf(
            Track("1437031362","A Star Is Born (2018)","https://is1-ssl.mzstatic.com/image/thumb/Video115/v4/a2/26/fd/a226fd77-c80b-5ee7-e40f-6a0222e1645d/pr_source.jpg/100x100bb.jpg","AUD"),
            Track("305571592","A Star Is Born (1976)","https://is1-ssl.mzstatic.com/image/thumb/Video/b8/35/bd/mzi.isxnlfla.jpg/100x100bb.jpg","AUD"),
            Track("379387379","A Star Is Born (1954)","https://is1-ssl.mzstatic.com/image/thumb/Video116/v4/b3/9c/91/b39c9144-3cc1-efea-5b4a-d08aee2ae5d1/pr_source.jpg/100x100bb.jpg","AUD"),
        )
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(tracks))
        mockWebServer.enqueue(expectedResponse)

        val actualResponse = trackRemoteSource.searchTracks("A star is born")

        assertEquals(actualResponse.resultCount, 3)
        assertEquals(actualResponse.results?.get(0)?.id, "1437031362")
        assertEquals(actualResponse.results?.get(1)?.name, "A Star Is Born (1976)")
        assertEquals(actualResponse.results?.get(2)?.currency, "AUD")
    }
}