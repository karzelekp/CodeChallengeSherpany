package pl.karzelek.codechallengesherpany.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import pl.karzelek.codechallengesherpany.R
import pl.karzelek.codechallengesherpany.di.ViewModelFactory
import pl.karzelek.codechallengesherpany.mainComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy { ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java) }
    private val adapter by lazy { PostsAdapter(this, viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainComponent().inject(this)

        viewModel.postsWithUsers.observe(this, {
            adapter.list = it
        })

        setContentView(R.layout.activity_main)
        setTitle(R.string.challenge_accepted)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }
}