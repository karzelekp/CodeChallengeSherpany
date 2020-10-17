package pl.karzelek.codechallengesherpany.mainactivity

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.karzelek.codechallengesherpany.db.PostWithUser
import pl.karzelek.codechallengesherpany.entities.Post
import pl.karzelek.codechallengesherpany.entities.User

class PostsDiffCallbackTest {

    @Test
    fun theSameList() {
        //given
        val list = listOf(PostWithUser(post1, user))
        //when
        val postsDiffCallback = PostsDiffCallback(list, list)
        //then
        assertTrue(postsDiffCallback.areItemsTheSame(0, 0))
        assertTrue(postsDiffCallback.areContentsTheSame(0, 0))
    }

    @Test
    fun reversedList() {
        //given
        val list1 = listOf(PostWithUser(post1, user), PostWithUser(post2, user))
        val reversedList = listOf(PostWithUser(post2, user), PostWithUser(post1, user))
        //when
        val postsDiffCallback = PostsDiffCallback(list1, reversedList)
        //then
        assertTrue(postsDiffCallback.areItemsTheSame(0, 1))
        assertTrue(postsDiffCallback.areContentsTheSame(0, 1))
        assertTrue(postsDiffCallback.areItemsTheSame(1, 0))
        assertTrue(postsDiffCallback.areContentsTheSame(1, 0))
        assertFalse(postsDiffCallback.areItemsTheSame(0, 0))
        assertFalse(postsDiffCallback.areContentsTheSame(0, 0))
    }

    companion object {
        private val user = User(null, null, null, null, null, null, null, null)
        val post1 = Post(1, null, null, null)
        val post2 = Post(2, null, null, null)
    }
}