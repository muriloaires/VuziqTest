package com.aires.murilo.vuziqtest.ui.albums.mvp

import com.aires.murilo.vuziqtest.data.db.model.Album
import com.aires.murilo.vuziqtest.ui.base.MvpPresenter
import com.aires.murilo.vuziqtest.ui.base.MvpView

interface AlbumsMvpPresenter<V : MvpView> : MvpPresenter<V> {

    /**
     * Return the number of favorite albums
     */
    fun getAlbumsCount(): Int

    /**
     * Return the List of favorite albums
     */
    fun getAlbums(): List<Album>

    /**
     * Callback to user's album selection
     */
    fun onAlbumSelected(position: Int)
}