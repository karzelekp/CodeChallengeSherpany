package pl.karzelek.codechallengesherpany.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.adapter.rxjava3.Result
import retrofit2.http.GET

interface Api {
    @GET("posts")
    fun posts(): Observable<Result<List<Post>>>

    @GET("users")
    fun users(): Observable<Result<List<User>>>

    @GET("albums")
    fun albums(): Observable<Result<List<Album>>>

    @GET("photos")
    fun photos(): Observable<Result<List<Photo>>>
}