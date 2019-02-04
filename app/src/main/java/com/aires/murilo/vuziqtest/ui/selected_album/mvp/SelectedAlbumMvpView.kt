package com.aires.murilo.vuziqtest.ui.selected_album.mvp

import com.aires.murilo.vuziqtest.ui.base.MvpView
import com.google.android.exoplayer2.SimpleExoPlayer

interface SelectedAlbumMvpView : MvpView {
    /**
     * Notify the adapter that the data set changed
     */
    fun notifyDatasetChanged()

    /**
     * Change the Activity's title
     */
    fun setToolbarTitle(name: String)

    /**
     * Set the album name
     */
    fun setAlbumName(name: String)

    /**
     * Set the artist name
     */
    fun setArtistName(artist: String)

    /**
     * Load the specified URL into the album ImageView
     */
    fun loadAlbumPic(imageUrl: String)

    /**
     * Attach the ExoPlayer to the ControlPlayerView
     */
    fun attachPlayerToPlayerView(player: SimpleExoPlayer)
}