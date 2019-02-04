package com.aires.murilo.vuziqtest.utils

import android.content.Context
import android.net.Uri
import com.aires.murilo.vuziqtest.data.db.model.Song
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class PlayerUtils {
    companion object {

        fun getSimplePlayer(context: Context): SimpleExoPlayer {
            val bandwidthMeter = DefaultBandwidthMeter()
            val trackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
            val dateSourceFactory =
                DefaultDataSourceFactory(
                    context, Util.getUserAgent(context, context.packageName),
                    bandwidthMeter
                )

            return ExoPlayerFactory.newSimpleInstance(
                context,
                DefaultTrackSelector(trackSelectionFactory)
            )
        }

        fun getConcatenatingMediaSource(songs: List<Song>, context: Context): ConcatenatingMediaSource {
            val bandwidthMeter = DefaultBandwidthMeter()
            val dateSourceFactory =
                DefaultDataSourceFactory(
                    context,
                    Util.getUserAgent(context, context.packageName),
                    bandwidthMeter
                )
            val mediaSources = arrayOfNulls<MediaSource>(songs.size)

            for (i in mediaSources.indices) {
                val songUri = songs[i].mp3StreamUrl
                mediaSources[i] = ExtractorMediaSource.Factory(dateSourceFactory).createMediaSource(Uri.parse(songUri))
            }
            return ConcatenatingMediaSource(*mediaSources)
        }
    }
}