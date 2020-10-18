package pl.karzelek.codechallengesherpany.detailview

import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Post

data class DetailViewState(
    val isLoading: Boolean = false,
    val post: Post? = null,
    val albums: List<Album> = emptyList()
)