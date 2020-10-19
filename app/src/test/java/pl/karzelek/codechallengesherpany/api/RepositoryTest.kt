package pl.karzelek.codechallengesherpany.api

import android.util.Log
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test
import pl.karzelek.codechallengesherpany.db.*
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Photo
import pl.karzelek.codechallengesherpany.entities.Post
import pl.karzelek.codechallengesherpany.entities.User

@ExperimentalCoroutinesApi
class RepositoryTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }

    @Test
    fun onAppColdStarted() {

        //given
        val postDao: PostDao = mockk<PostDao>().apply {
            every { insertAll(any()) } returns Unit
        }
        val userDao = mockk<UserDao>().apply {
            every { insertAll(any()) } returns Unit
        }
        val albumsDao = mockk<AlbumDao>().apply {
            every { insertAll(any()) } returns Unit
        }
        val photoDao: PhotoDao = mockk<PhotoDao>().apply {
            every { insertAll(any()) } returns Unit
        }

        val database = mockk<ChallengeDatabase>().apply {
            every { postDao() } returns postDao
            every { userDao() } returns userDao
            every { albumDao() } returns albumsDao
            every { photoDao() } returns photoDao

            every { runInTransaction(any()) }.answers {
                firstArg<Runnable>().run()
            }
        }

        val posts = listOf(Post(null, null, null, null))
        val users = listOf(User(null, null, null, null, null, null, null, null))
        val albums = listOf(Album(null, null, null))
        val photos = listOf(Photo(null, null, null, null, null))

        val api = mockk<Api>().apply {
            coEvery { suspendPosts() } returns posts
            coEvery { suspendUsers() } returns users
            coEvery { suspendAlbums() } returns albums
            coEvery { suspendPhotos() } returns photos
        }

        val repository = Repository(api, database, mockk(), testCoroutineDispatcher, testCoroutineDispatcher)

        //when
        repository.onAppColdStarted()

        //then
        verify { userDao.insertAll(users) }
        verify { postDao.insertAll(posts) }
        verify { albumsDao.insertAll(albums) }
        verify { photoDao.insertAll(photos) }
    }
}