package android.practice.com.youtuberxkotlin.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET("/youtube/v3/search")
    fun search(@Query("q") query: String? = null,
               @Query("pageToken") pageToken: String? = "",
               @Query("part") part: String = "snippet",
               @Query("type") type: String = "video",
               @Query("key") key: String = "",//input your api key
               @Query("maxResults") perPage: Int = 20): Single<apiResponse>
}