package pl.karzelek.codechallengesherpany.mainactivity

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pl.karzelek.codechallengesherpany.db.*
import pl.karzelek.codechallengesherpany.detailview.DetailViewState
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Post
import pl.karzelek.codechallengesherpany.entities.User

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun defaultState() {
        //when
        val mainViewModel = MainViewModel(mockDatabase())
        //then
        assertEquals(DetailViewState(false, null, emptyList()), mainViewModel.detailViewState.value)
    }

    @Test
    fun selectPost() {
        //given
        val userId = 13
        val albums = listOf(AlbumWithPhotos(Album(null, userId, null), listOf()))
        val albumDao: AlbumDao = mockk()
        coEvery { albumDao.getAlbumsForUser(userId) } returns albums
        val database = mockDatabase()
        every { database.albumDao() } returns albumDao
        val mainViewModel = MainViewModel(database)
        val post = Post(null, null, null, null)
        val postWithUser = PostWithUser(
            post,
            User(userId, null, null, null, null, null, null, null)
        )

        val observer = spyk<Observer<DetailViewState>>()
        mainViewModel.detailViewState.observeForever(observer)
        //when
        mainViewModel.onPostSelected(postWithUser)
        //then
        val expectedResult = DetailViewState(false, post, albums)
        verify(exactly = 1) { observer.onChanged(DetailViewState(true, null, emptyList())) }
        verify(exactly = 1) { observer.onChanged(expectedResult) }
        assertEquals(expectedResult, mainViewModel.detailViewState.value)
    }

    private fun mockDatabase(): ChallengeDatabase {
        val database: ChallengeDatabase = mockk()
        val postDao: PostDao = mockk()
        every { postDao.getAllPostsWithUsers() } returns mockk()
        every { database.postDao() } returns postDao
        return database
    }
}