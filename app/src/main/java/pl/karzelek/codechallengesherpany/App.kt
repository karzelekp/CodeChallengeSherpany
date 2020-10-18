package pl.karzelek.codechallengesherpany

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import pl.karzelek.codechallengesherpany.api.Repository
import pl.karzelek.codechallengesherpany.di.DaggerMainComponent
import pl.karzelek.codechallengesherpany.di.MainComponent
import javax.inject.Inject

class App : Application() {
    val mainComponent: MainComponent = DaggerMainComponent.factory().create(this)

    @Inject
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        mainComponent.inject(this)
        repository.onAppColdStarted()
    }
}

fun Activity.mainComponent(): MainComponent = (application as App).mainComponent
fun Fragment.mainComponent(): MainComponent = requireActivity().mainComponent()
