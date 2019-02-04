package com.aires.murilo.vuziqtest.ui.albums

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textAlbumName = view.textAlbumName!!
    val textArtist = view.textArtist!!
    val textReleaseDate = view.textReleaseDate!!
    val albumPicUrl = view.imageAlgumPic!!
    val rootView = view.rootView!!
}