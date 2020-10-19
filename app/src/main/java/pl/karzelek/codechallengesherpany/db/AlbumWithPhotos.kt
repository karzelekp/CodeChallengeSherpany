package pl.karzelek.codechallengesherpany.db

import androidx.room.Embedded
import androidx.room.Relation
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Photo

data class AlbumWithPhotos(
    @Embedded
    val album: Album,

    @Relation(entity = Photo::class, parentColumn = "id", entityColumn = "album_id")
    val photos: List<Photo>
)
    fun Iterable<AlbumWithPhotos>.flatList() = flatMap { listOf(it.album) + it.photos }