package pl.karzelek.codechallengesherpany.detailview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pl.karzelek.codechallengesherpany.databinding.DetailFragmentBinding
import pl.karzelek.codechallengesherpany.di.ViewModelFactory
import pl.karzelek.codechallengesherpany.mainComponent
import pl.karzelek.codechallengesherpany.mainactivity.MainViewModel
import javax.inject.Inject

class DetailViewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy { ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        DetailFragmentBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
            it.executePendingBindings()
        }.root

    companion object {
        const val TAG = "DetailViewFragment"
    }
}