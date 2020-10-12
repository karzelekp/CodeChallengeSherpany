package pl.karzelek.codechallengesherpany.di

import dagger.Component
import pl.karzelek.codechallengesherpany.MainActivity
import javax.inject.Singleton

@Component(modules = [MainModule::class])
@Singleton
interface MainComponent {
    fun inject(activity: MainActivity)
}