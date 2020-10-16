package pl.karzelek.codechallengesherpany.api

import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Photo
import pl.karzelek.codechallengesherpany.entities.Post
import pl.karzelek.codechallengesherpany.entities.User
import retrofit2.http.GET

interface Api {
    @GET("posts")
    suspend fun suspendPosts(): List<Post>

    @GET("users")
    suspend fun suspendUsers(): List<User>

    @GET("albums")
    suspend fun suspendAlbums(): List<Album>

    @GET("photos")
    suspend fun suspendPhotos(): List<Photo>
}