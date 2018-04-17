package android.practice.com.youtuberxkotlin.viewmodel

import android.arch.lifecycle.ViewModel
import android.practice.com.youtuberxkotlin.network.YoutubeService
import android.practice.com.youtuberxkotlin.network.apiResponse
import android.practice.com.youtuberxkotlin.vo.Video
import io.reactivex.Single
import javax.inject.Inject

class ListViewModel @Inject constructor(private val youtubeService: YoutubeService) : ViewModel() {

    var searchKeyword: String = ""
    var nextPageToken = ""
    fun search(keyword: String): Single<List<Video>> {
        searchKeyword = keyword
        return youtubeService.search(keyword).map { response: apiResponse -> convertApiResponseToVideo(response) }
    }

    fun loadMore(): Single<List<Video>> {
        return youtubeService.search(searchKeyword, nextPageToken).map { response: apiResponse -> convertApiResponseToVideo(response) }
    }

    fun convertApiResponseToVideo(response: apiResponse): List<Video> {
        nextPageToken = response.nextPageToken
        val list = mutableListOf<Video>()
        for (i in response.items) {
            list.add(Video(i.snippet.title, i.snippet.thumbnails.medium.url))
        }
        return list
    }
}