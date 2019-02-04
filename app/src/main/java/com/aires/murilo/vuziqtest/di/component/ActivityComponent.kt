package com.aires.murilo.vuziqtest.di.component

import com.aires.murilo.vuziqtest.ui.main.MainActivity
import com.aires.murilo.vuziqtest.di.PerActivity
import com.aires.murilo.vuziqtest.di.module.ActivityModule
import com.aires.murilo.vuziqtest.ui.albums.AlbumsFragment
import com.aires.murilo.vuziqtest.ui.selected_album.SelectedAlbumFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(albumsFragment: AlbumsFragment)

    fun inject(selectedAlbumFragment: SelectedAlbumFragment)
}