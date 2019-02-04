package com.aires.murilo.vuziqtest.data.db.model

import java.util.ArrayList

data class Album(
    var id: Long, var name: String, var releaseDate: Int,
    var imageUrl: String, var songs: ArrayList<Song>,
    var artist: String
) {
}