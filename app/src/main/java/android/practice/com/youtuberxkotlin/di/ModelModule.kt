package android.practice.com.youtuberxkotlin.di

import android.practice.com.youtuberxkotlin.network.YoutubeService
import android.practice.com.youtuberxkotlin.viewmodel.ListViewModel
import dagger.Module
import dagger.Provides

@Module
class ModelModule {

    @Provides
    fun provideListViewModel(youtubeService: YoutubeService): ListViewModel {
        return ListViewModel(youtubeService)
    }
}