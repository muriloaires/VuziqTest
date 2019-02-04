package com.aires.murilo.vuziqtest.ui.selected_album


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.aires.murilo.vuziqtest.ui.main.MainActivity

import com.aires.murilo.vuziqtest.R
import com.aires.murilo.vuziqtest.ui.base.BaseFragment
import com.aires.murilo.vuziqtest.ui.selected_album.mvp.SelectedAlbumMvpPresenter
import com.aires.murilo.vuziqtest.ui.selected_album.mvp.SelectedAlbumMvpView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_selected_album.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class SelectedAlbumFragment : BaseFragment(), SelectedAlbumMvpView {

    @Inject
    lateinit var mPresenter: SelectedAlbumMvpPresenter<SelectedAlbumMvpView>

    lateinit var mAdapter: SongsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivityComponent()?.inject(this)
        mPresenter.onAttach(this)
        mPresenter.handleArguments(arguments!!)


        return inflater.inflate(R.layout.fragment_selected_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = SongsAdapter(mPresenter)
        recyclerSongs.layoutManager = LinearLayoutManager(getBaseActivity()!!)
        recyclerSongs.adapter = mAdapter

        val callback = ItemTouchHelperCallback(mAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerSongs)

        mPresenter.onViewReady()
    }


    override fun onDestroy() {
        mPresenter.onDetach()
        mPresenter.onFragmentDestroyed()
        super.onDestroy()
    }

    override fun notifyDatasetChanged() {
        mAdapter.notifyDataSetChanged()
    }

    override fun setToolbarTitle(name: String) {
        (getBaseActivity() as MainActivity).setToolbarTitle(name)
    }

    override fun setAlbumName(name: String) {
        textAlbumName.text = name
    }

    override fun setArtistName(artist: String) {
        textArtist.text = artist
    }

    override fun loadAlbumPic(imageUrl: String) {
        Picasso.get().load(imageUrl).into(imgAlbumPic)
    }

    override fun attachPlayerToPlayerView(player: SimpleExoPlayer) {
        playerView.player = player
        playerView.showTimeoutMs = 0
    }
}
