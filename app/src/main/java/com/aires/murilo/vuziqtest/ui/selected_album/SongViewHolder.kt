package com.aires.murilo.vuziqtest.ui.selected_album

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.song_item.view.*

class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textSongName = view.textSongName!!
    val textSongDuration = view.textSongDuration!!
    val textPosition = view.textPosition!!
    val root = view.root!!
}