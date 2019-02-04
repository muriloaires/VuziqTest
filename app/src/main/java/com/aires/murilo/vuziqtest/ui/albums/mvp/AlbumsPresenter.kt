package com.aires.murilo.vuziqtest.ui.albums.mvp

import com.aires.murilo.vuziqtest.R
import com.aires.murilo.vuziqtest.data.DataManager
import com.aires.murilo.vuziqtest.data.db.model.Album
import com.aires.murilo.vuziqtest.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AlbumsPresenter<V : AlbumsMvpView> @Inject constructor(
    dataManager: DataManager,
    mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, mCompositeDisposable),
    AlbumsMvpPresenter<V> {

    private val mAlbums = mutableListOf<Album>()

    override fun getAlbumsCount(): Int = mAlbums.size

    override fun getAlbums(): List<Album> = mAlbums

    override fun onViewReady() {
        mAlbums.clear()
        mCompositeDisposable.add(dataManager.loadFavoriteAlbums().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { albums ->
                if (albums.isNullOrEmpty()) {
                    getMvpView()?.onError(R.string.something_wrong_occurred)
                } else {
                    this.mAlbums.addAll(albums)
                    getMvpView()?.notifyDataSetChanged()
                }
            })
    }

    override fun onAlbumSelected(position: Int) {
        getMvpView()?.showSelectedAlbumFragment(PARAMETER_ALBUM_ID, mAlbums[position].id)
    }

    companion object {
        const val PARAMETER_ALBUM_ID = "album_id"
    }
}