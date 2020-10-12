package pl.karzelek.codechallengesherpany

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import pl.karzelek.codechallengesherpany.di.DaggerMainComponent
import pl.karzelek.codechallengesherpany.di.MainComponent

class App : Application() {
    val mainComponent: MainComponent = DaggerMainComponent.create()
}

fun Activity.mainComponent(): MainComponent = (application as App).mainComponent
fun Fragment.mainComponent(): MainComponent = requireActivity().mainComponent()
