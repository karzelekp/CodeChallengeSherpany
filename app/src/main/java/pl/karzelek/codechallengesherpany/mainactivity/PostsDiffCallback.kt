package pl.karzelek.codechallengesherpany.mainactivity

import androidx.recyclerview.widget.DiffUtil
import pl.karzelek.codechallengesherpany.db.PostWithUser

class PostsDiffCallback(private val oldList: List<PostWithUser>, private val newList: List<PostWithUser>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].post.id == newList[newItemPosition].post.id

    /**
     * works as areItemsTheSame in this implementation
     * this implementation assumes the source of the data never changes and the same id means the same content
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = areItemsTheSame(oldItemPosition, newItemPosition)
}