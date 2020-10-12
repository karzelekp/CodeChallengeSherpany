package pl.karzelek.codechallengesherpany.api

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("posts")
    fun posts(): Call<List<Post>>

    @GET("users")
    fun users(): Call<List<User>>

    @GET("albums")
    fun albums(): Call<List<Album>>

    @GET("photos")
    fun photos(): Call<List<Photo>>
}