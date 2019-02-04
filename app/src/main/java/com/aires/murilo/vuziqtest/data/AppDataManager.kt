package com.aires.murilo.vuziqtest.data

import android.content.Context
import com.aires.murilo.vuziqtest.data.db.DbHelper
import com.aires.murilo.vuziqtest.data.db.model.Album
import com.aires.murilo.vuziqtest.di.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject

class AppDataManager @Inject constructor(
    @ApplicationContext val context: Context, val mDbHelper: DbHelper
) : DataManager {

    override fun getAppContext(): Context {
        return context
    }

    override fun loadFavoriteAlbums(): Observable<List<Album>> {
        return mDbHelper.loadFavoriteAlbums()
    }

    override fun loadAlbumByID(id: Long): Observable<Album> {
        return mDbHelper.loadAlbumByID(id)
    }
}