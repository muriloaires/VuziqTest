package com.aires.murilo.vuziqtest.ui.main

import android.os.Bundle
import com.aires.murilo.vuziqtest.R
import com.aires.murilo.vuziqtest.ui.albums.AlbumsFragment
import com.aires.murilo.vuziqtest.ui.base.BaseActivity
import com.aires.murilo.vuziqtest.ui.selected_album.SelectedAlbumFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        showAlbumsFragment()
    }

    private fun showAlbumsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeholder, AlbumsFragment())
            .commit()
    }

    fun showSelectedAlbumFragment(bundle: Bundle) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.placeholder, SelectedAlbumFragment().apply { arguments = bundle })
            .addToBackStack("main")
            .commit()
    }

    fun setToolbarTitle(name: String) {
        toolbar.title = name
    }

}
