package pl.karzelek.codechallengesherpany.di

import dagger.BindsInstance
import dagger.Component
import pl.karzelek.codechallengesherpany.App
import pl.karzelek.codechallengesherpany.mainactivity.MainActivity
import javax.inject.Singleton

@Component(modules = [MainModule::class, ViewModelModule::class])
@Singleton
interface MainComponent {

    fun inject(app: App)
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): MainComponent
    }
}