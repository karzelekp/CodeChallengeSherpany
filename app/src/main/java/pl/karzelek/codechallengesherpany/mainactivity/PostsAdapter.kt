package pl.karzelek.codechallengesherpany.mainactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.karzelek.codechallengesherpany.databinding.PostRowBinding
import pl.karzelek.codechallengesherpany.db.PostWithUser

class PostsAdapter(private val context: Context) : RecyclerView.Adapter<PostsAdapter.Holder>() {

    var list = emptyList<PostWithUser>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        PostRowBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class Holder(private val binding: PostRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(postWithUser: PostWithUser) {
            binding.postWithUser = postWithUser
            binding.executePendingBindings()
        }
    }
}