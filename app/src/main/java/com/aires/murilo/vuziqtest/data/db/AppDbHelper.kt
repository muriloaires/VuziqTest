package com.aires.murilo.vuziqtest.data.db

import android.content.Context
import com.aires.murilo.vuziqtest.data.db.model.Album
import com.aires.murilo.vuziqtest.di.ApplicationContext
import com.google.gson.Gson
import io.reactivex.Observable
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import com.google.gson.reflect.TypeToken


class AppDbHelper @Inject constructor(@ApplicationContext val context: Context) : DbHelper {

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    override fun loadFavoriteAlbums(): Observable<List<Album>> {
        return Observable.fromCallable<List<Album>> {
            val favoriteAlbums = getMockedAlbums()
            favoriteAlbums
        }
    }


    override fun loadAlbumByID(id: Long): Observable<Album> {
        return Observable.fromCallable<Album> {
            val albums = getMockedAlbums()
            var selectedAlbum: Album? = null
            albums.forEach {
                if (it.id == id) {
                    selectedAlbum = it
                }
            }
            selectedAlbum
        }


    }

    /**
     * Real DB doesn't exist, but we load the albums from a existing json file at the assets folder
     */
    private fun getMockedAlbums(): List<Album> {
        var json: String = ""
        try {
            val inputStream = context.assets.open("albums.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return Gson().fromJson<List<Album>>(json)
    }

}