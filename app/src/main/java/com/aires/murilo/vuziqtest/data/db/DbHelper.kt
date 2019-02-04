package com.aires.murilo.vuziqtest.data.db

import com.aires.murilo.vuziqtest.data.db.model.Album
import io.reactivex.Observable

interface DbHelper {

    /**
     * Load all favorite albums from database
     */
    fun loadFavoriteAlbums(): Observable<List<Album>>

    /**
     * Load an specific album by its id
     */
    fun loadAlbumByID(id: Long): Observable<Album>
}