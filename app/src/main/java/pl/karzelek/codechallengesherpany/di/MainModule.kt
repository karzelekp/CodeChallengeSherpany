package pl.karzelek.codechallengesherpany.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import pl.karzelek.codechallengesherpany.App
import pl.karzelek.codechallengesherpany.api.Api
import pl.karzelek.codechallengesherpany.db.ChallengeDatabase
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object MainModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context) = Room.databaseBuilder(
        appContext,
        ChallengeDatabase::class.java,
        ChallengeDatabase.NAME
    ).build()

    @Provides
    @Singleton
    fun provideAppContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

}