package pl.karzelek.codechallengesherpany.di

import dagger.BindsInstance
import dagger.Component
import pl.karzelek.codechallengesherpany.App
import pl.karzelek.codechallengesherpany.MainActivity
import javax.inject.Singleton

@Component(modules = [MainModule::class])
@Singleton
interface MainComponent {
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): MainComponent
    }
}