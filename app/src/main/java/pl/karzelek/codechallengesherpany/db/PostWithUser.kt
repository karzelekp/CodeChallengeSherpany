package pl.karzelek.codechallengesherpany.db

import androidx.room.Embedded
import androidx.room.Relation
import pl.karzelek.codechallengesherpany.entities.Post
import pl.karzelek.codechallengesherpany.entities.User

data class PostWithUser(
    @Embedded
    val post: Post,
    @Relation(entity = User::class, parentColumn = "user_id", entityColumn = "id")
    val user: User
)