package pl.karzelek.codechallengesherpany.detailview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.detail_fragment.*
import pl.karzelek.codechallengesherpany.databinding.DetailFragmentBinding
import pl.karzelek.codechallengesherpany.db.AlbumWithPhotos
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailViewState.observe(viewLifecycleOwner) {
            setAdapter(it.albums)
        }
    }

    private fun setAdapter(photos: List<AlbumWithPhotos>) {
        val adapter = AlbumsAdapter(requireContext(), photos)
        albums.adapter = adapter
        albums.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (adapter.isHeader(position)) {
                        SPAN_COUNT
                    } else {
                        1
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "DetailViewFragment"
        const val SPAN_COUNT = 3
    }
}