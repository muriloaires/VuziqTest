package com.aires.murilo.vuziqtest.ui.selected_album.mvp

import android.os.Bundle
import android.util.Log
import com.aires.murilo.vuziqtest.data.DataManager
import com.aires.murilo.vuziqtest.data.db.model.Album
import com.aires.murilo.vuziqtest.data.db.model.Song
import com.aires.murilo.vuziqtest.ui.albums.mvp.AlbumsPresenter
import com.aires.murilo.vuziqtest.ui.base.BasePresenter
import com.aires.murilo.vuziqtest.utils.PlayerUtils
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.aires.murilo.vuziqtest.R
import java.util.*
import javax.inject.Inject


class SelectedAlbumPresenter<V : SelectedAlbumMvpView> @Inject constructor(
    dataManager: DataManager,
    mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(dataManager, mCompositeDisposable), SelectedAlbumMvpPresenter<V> {

    private var album: Album? = null
    private var mSelectedAlbumId = -1L
    private lateinit var concatenatingMediaSource: ConcatenatingMediaSource

    private var exoPlayer: SimpleExoPlayer? = null

    override fun handleArguments(arguments: Bundle) {
        mSelectedAlbumId = arguments.getLong(AlbumsPresenter.PARAMETER_ALBUM_ID)
    }

    override fun onViewReady() {
        mCompositeDisposable.add(dataManager.loadAlbumByID(mSelectedAlbumId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.album = it
                configureExoPlayer()
                getMvpView()?.setToolbarTitle(album!!.name)
                getMvpView()?.setAlbumName(album!!.name)
                getMvpView()?.setArtistName(album!!.artist)
                getMvpView()?.loadAlbumPic(album!!.imageUrl)
                getMvpView()?.notifyDatasetChanged()
            })
    }

    private fun configureExoPlayer() {
        concatenatingMediaSource = getMediaSourceFromAlbum()
        exoPlayer = PlayerUtils.getSimplePlayer(dataManager.getAppContext())
        exoPlayer!!.prepare(concatenatingMediaSource)
        exoPlayer!!.addListener(object : Player.EventListener {
            /**
             * Called when the current playback parameters change. The playback parameters may change due to
             * a call to [.setPlaybackParameters], or the player itself may change
             * them (for example, if audio playback switches to passthrough mode, where speed adjustment is
             * no longer possible).
             *
             * @param playbackParameters The playback parameters.
             */
            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {

            }

            /**
             * Called when all pending seek requests have been processed by the player. This is guaranteed
             * to happen after any necessary changes to the player state were reported to
             * [.onPlayerStateChanged].
             */
            override fun onSeekProcessed() {

            }

            /**
             * Called when the available or selected tracks change.
             *
             * @param trackGroups The available tracks. Never null, but may be of length zero.
             * @param trackSelections The track selections for each renderer. Never null and always of
             * length [.getRendererCount], but may contain null elements.
             */
            override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
                exoPlayer!!.currentPosition
                Log.d("", "")
            }

            /**
             * Called when an error occurs. The playback state will transition to [.STATE_IDLE]
             * immediately after this method is called. The player instance can still be used, and
             * [.release] must still be called on the player should it no longer be required.
             *
             * @param error The error.
             */
            override fun onPlayerError(error: ExoPlaybackException?) {
                getMvpView()?.onError(R.string.error_loading_media)
            }

            /**
             * Called when the player starts or stops loading the source.
             *
             * @param isLoading Whether the source is currently being loaded.
             */
            override fun onLoadingChanged(isLoading: Boolean) {

            }

            /**
             * Called when a position discontinuity occurs without a change to the timeline. A position
             * discontinuity occurs when the current window or period index changes (as a result of playback
             * transitioning from one period in the timeline to the next), or when the playback position
             * jumps within the period currently being played (as a result of a seek being performed, or
             * when the source introduces a discontinuity internally).
             *
             *
             * When a position discontinuity occurs as a result of a change to the timeline this method is
             * *not* called. [.onTimelineChanged] is called in this
             * case.
             *
             * @param reason The [DiscontinuityReason] responsible for the discontinuity.
             */
            override fun onPositionDiscontinuity(reason: Int) {

            }

            /**
             * Called when the value of [.getRepeatMode] changes.
             *
             * @param repeatMode The [RepeatMode] used for playback.
             */
            override fun onRepeatModeChanged(repeatMode: Int) {

            }

            /**
             * Called when the value of [.getShuffleModeEnabled] changes.
             *
             * @param shuffleModeEnabled Whether shuffling of windows is enabled.
             */
            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {

            }

            /**
             * Called when the timeline and/or manifest has been refreshed.
             *
             *
             * Note that if the timeline has changed then a position discontinuity may also have occurred.
             * For example, the current period index may have changed as a result of periods being added or
             * removed from the timeline. This will *not* be reported via a separate call to
             * [.onPositionDiscontinuity].
             *
             * @param timeline The latest timeline. Never null, but may be empty.
             * @param manifest The latest manifest. May be null.
             * @param reason The [TimelineChangeReason] responsible for this timeline change.
             */
            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {
                Log.d("", "")
            }

            /**
             * Called when the value returned from either [.getPlayWhenReady] or
             * [.getPlaybackState] changes.
             *
             * @param playWhenReady Whether playback will proceed when ready.
             * @param playbackState One of the `STATE` constants.
             */
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {

            }
        })
        getMvpView()?.attachPlayerToPlayerView(exoPlayer!!)
    }

    private fun getMediaSourceFromAlbum(): ConcatenatingMediaSource {
        return PlayerUtils.getConcatenatingMediaSource(album!!.songs, dataManager.getAppContext())
    }

    override fun getSongsCount(): Int {
        return if (album == null) {
            0
        } else {
            album!!.songs.size
        }
    }


    override fun getSongsList(): ArrayList<Song> = album!!.songs

    override fun onSongSelected(position: Int) {
        exoPlayer!!.seekTo(position, C.TIME_UNSET)
        exoPlayer!!.playWhenReady = true

    }

    override fun onFragmentDestroyed() {
        if (exoPlayer != null) {
            exoPlayer!!.release()
            exoPlayer = null
        }
    }

    override fun onSongsSwapped(fromPosition: Int, toPosition: Int) {
        concatenatingMediaSource.moveMediaSource(fromPosition, toPosition)
    }
}