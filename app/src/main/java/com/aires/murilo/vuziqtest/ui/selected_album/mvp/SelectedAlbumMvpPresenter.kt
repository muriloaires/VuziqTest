package com.aires.murilo.vuziqtest.ui.selected_album.mvp

import android.os.Bundle
import com.aires.murilo.vuziqtest.data.db.model.Song
import com.aires.murilo.vuziqtest.ui.base.MvpPresenter
import com.aires.murilo.vuziqtest.ui.base.MvpView
import java.util.ArrayList

interface SelectedAlbumMvpPresenter<V : MvpView> : MvpPresenter<V> {

    /**
     * Return the number of tracks in the selected album
     */
    fun getSongsCount(): Int

    /**
     * Return a List of the album's songs
     */
    fun getSongsList(): ArrayList<Song>

    /**
     * Handle the data of the incoming bundle
     */
    fun handleArguments(arguments: Bundle)

    /**
     * Handle the user selection of a song
     */
    fun onSongSelected(position: Int)

    /**
     * a Callback to the end of the Fragment life-cycle
     */
    fun onFragmentDestroyed()

    /**
     * A Callback to handle the swapping of the songs positions
     */
    fun onSongsSwapped(fromPosition: Int, toPosition: Int)
}