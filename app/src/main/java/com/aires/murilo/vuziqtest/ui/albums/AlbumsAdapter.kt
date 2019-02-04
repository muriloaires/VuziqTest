package com.aires.murilo.vuziqtest.ui.albums

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aires.murilo.vuziqtest.R
import com.aires.murilo.vuziqtest.ui.albums.mvp.AlbumsMvpPresenter
import com.aires.murilo.vuziqtest.ui.albums.mvp.AlbumsMvpView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class AlbumsAdapter(private val mPresenter: AlbumsMvpPresenter<AlbumsMvpView>) :
    RecyclerView.Adapter<AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(view)
    }


    override fun getItemCount(): Int = mPresenter.getAlbumsCount()


    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = mPresenter.getAlbums()[position]
        Picasso.get().load(Uri.parse(album.imageUrl)).into(holder.albumPicUrl)
        holder.textAlbumName.text = album.name
        holder.textArtist.text = album.artist
        holder.textReleaseDate.text = album.releaseDate.toString()
        holder.rootView.setOnClickListener {
            mPresenter.onAlbumSelected(position)
        }
    }
}