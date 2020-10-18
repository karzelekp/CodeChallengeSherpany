package pl.karzelek.codechallengesherpany.detailview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_row.view.*
import kotlinx.android.synthetic.main.photo_row.view.*
import pl.karzelek.codechallengesherpany.R
import pl.karzelek.codechallengesherpany.db.AlbumWithPhotos
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Photo

class AlbumsAdapter(private val context: Context, list: List<AlbumWithPhotos>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val flatList = list.flatMap { listOf(it.album) + it.photos }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER) {
            HeaderHolder(LayoutInflater.from(context).inflate(R.layout.album_row, parent, false))
        } else {
            PhotoHolder(LayoutInflater.from(context).inflate(R.layout.photo_row, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (flatList[position] is Album) {
            HEADER
        } else {
            PHOTO
        }
    }

    fun isHeader(position: Int) = getItemViewType(position) == HEADER

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = flatList[position]
        if (holder is HeaderHolder && item is Album) {
            holder.title.text = item.title
        } else if (holder is PhotoHolder && item is Photo) {
            holder.url.text = item.url
        }
    }

    override fun getItemCount() = flatList.size

    class HeaderHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.album_title!!
    }

    class PhotoHolder(view: View) : RecyclerView.ViewHolder(view) {
        val url = view.url!!
    }

    companion object {
        private const val HEADER = 1
        private const val PHOTO = 2
    }
}
