package pl.karzelek.codechallengesherpany.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.karzelek.codechallengesherpany.databinding.PostRowBinding
import pl.karzelek.codechallengesherpany.db.PostWithUser

class PostsAdapter(
    private val context: Context,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<PostsAdapter.Holder>() {

    var list = emptyList<PostWithUser>()
        set(value) {
            val postsDiffCallback = PostsDiffCallback(field, value)
            field = value
            val calculateDiff = DiffUtil.calculateDiff(postsDiffCallback)
            calculateDiff.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        PostRowBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position], viewModel)
    }

    override fun getItemCount() = list.size

    class Holder(private val binding: PostRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postWithUser: PostWithUser, viewModel: MainViewModel) {
            binding.postWithUser = postWithUser
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}