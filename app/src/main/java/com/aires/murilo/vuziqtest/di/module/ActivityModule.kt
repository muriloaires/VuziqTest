package com.aires.murilo.vuziqtest.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.aires.murilo.vuziqtest.di.ActivityContext
import com.aires.murilo.vuziqtest.di.PerActivity
import com.aires.murilo.vuziqtest.ui.albums.mvp.AlbumsMvpPresenter
import com.aires.murilo.vuziqtest.ui.albums.mvp.AlbumsMvpView
import com.aires.murilo.vuziqtest.ui.albums.mvp.AlbumsPresenter
import com.aires.murilo.vuziqtest.ui.selected_album.mvp.SelectedAlbumMvpPresenter
import com.aires.murilo.vuziqtest.ui.selected_album.mvp.SelectedAlbumMvpView
import com.aires.murilo.vuziqtest.ui.selected_album.mvp.SelectedAlbumPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(val mActivity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = mActivity

    @Provides
    fun provideActivity(): AppCompatActivity = mActivity

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @PerActivity
    fun providesAlbumsMvpPresenter(mAlbumsPresenter: AlbumsPresenter<AlbumsMvpView>)
            : AlbumsMvpPresenter<AlbumsMvpView> = mAlbumsPresenter

    @Provides
    fun providesSelectedAlbumMvpPresenter(mPresenter: SelectedAlbumPresenter<SelectedAlbumMvpView>)
            : SelectedAlbumMvpPresenter<SelectedAlbumMvpView> = mPresenter
}