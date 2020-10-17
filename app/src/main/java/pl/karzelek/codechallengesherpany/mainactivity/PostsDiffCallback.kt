package pl.karzelek.codechallengesherpany.mainactivity

import androidx.recyclerview.widget.DiffUtil
import pl.karzelek.codechallengesherpany.db.PostWithUser

class PostsDiffCallback(private val oldList: List<PostWithUser>, private val newList: List<PostWithUser>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].post.id == newList[newItemPosition].post.id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = areItemsTheSame(oldItemPosition, newItemPosition)
}