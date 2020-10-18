package pl.karzelek.codechallengesherpany.db

import androidx.room.Embedded
import androidx.room.Relation
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.User

data class UserWithAlbums(
    @Embedded
    val user: User,

    @Relation(entity = Album::class, parentColumn = "id", entityColumn = "user_id")
    val albums: List<Album>
)