package pl.karzelek.codechallengesherpany.db

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Photo

class AlbumWithPhotosKtTest {

    @Test
    fun flatList() {
        //given
        val album = Album(null, null, null)
        val photo = Photo(null, null, null, null, null)
        val photo2 = Photo(null, null, null, null, null)
        val album2 = Album(null, null, null)
        val albumWithPhotos = AlbumWithPhotos(album, listOf(photo))
        val albumWithPhotos2 = AlbumWithPhotos(album2, listOf(photo2))
        val list = listOf(albumWithPhotos, albumWithPhotos2)
        //when
        val flatList = list.flatList()
        //then
        assertEquals(album, flatList[0])
        assertEquals(photo, flatList[1])
        assertEquals(album2, flatList[2])
        assertEquals(photo2, flatList[3])
        assertEquals(4, flatList.size)
    }
}