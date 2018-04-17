package android.practice.com.youtuberxkotlin.di

import android.practice.com.youtuberxkotlin.network.YoutubeService
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return retrofit2.Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    @NotNull
    fun provideYoutubeService(retrofit: Retrofit) = retrofit.create(YoutubeService::class.java)

}