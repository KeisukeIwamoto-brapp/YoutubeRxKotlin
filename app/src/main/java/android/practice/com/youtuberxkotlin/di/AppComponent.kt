package android.practice.com.youtuberxkotlin.di

import android.practice.com.youtuberxkotlin.ui.ListActivity
import android.practice.com.youtuberxkotlin.viewmodel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        ModelModule::class)
)
interface AppComponent {
    fun inject(activity: ListActivity)

    fun inject(viewModel: ListViewModel)
}