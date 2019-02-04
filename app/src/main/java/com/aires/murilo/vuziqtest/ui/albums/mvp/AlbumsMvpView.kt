package com.aires.murilo.vuziqtest.ui.albums.mvp

import com.aires.murilo.vuziqtest.ui.base.MvpView

interface AlbumsMvpView : MvpView {

    /**
     * Notify the adapter tha the data set have changed
     */
    fun notifyDataSetChanged()

    /**
     * Show SelectedAlbumFragment
     */
    fun showSelectedAlbumFragment(parameterKey: String, parameterValue: Long)

}
