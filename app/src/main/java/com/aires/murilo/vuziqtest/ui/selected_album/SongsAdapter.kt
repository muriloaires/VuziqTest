package com.aires.murilo.vuziqtest.ui.selected_album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aires.murilo.vuziqtest.R
import com.aires.murilo.vuziqtest.ui.selected_album.mvp.SelectedAlbumMvpPresenter
import com.aires.murilo.vuziqtest.ui.selected_album.mvp.SelectedAlbumMvpView
import java.util.*


class SongsAdapter(private val mPresenter: SelectedAlbumMvpPresenter<SelectedAlbumMvpView>) :
    RecyclerView.Adapter<SongViewHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int = mPresenter.getSongsCount()


    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = mPresenter.getSongsList()[position]
        holder.textSongDuration.text = song.duration
        holder.textSongName.text = song.name
        holder.textPosition.text = (position + 1).toString()
        holder.root.setOnClickListener {
            mPresenter.onSongSelected(position)
        }
    }


    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mPresenter.getSongsList(), i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mPresenter.getSongsList(), i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(fromPosition)
        notifyItemChanged(toPosition)
        mPresenter.onSongsSwapped(fromPosition, toPosition)
        return true
    }
}