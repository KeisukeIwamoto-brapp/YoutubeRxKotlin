package android.practice.com.youtuberxkotlin

import android.app.Application
import android.practice.com.youtuberxkotlin.di.*

class MainApplication : Application() {
    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .modelModule(ModelModule())
                .build()
    }

    fun getComponent() : AppComponent {
        return appComponent
    }
}