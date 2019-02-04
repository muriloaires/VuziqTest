package com.aires.murilo.vuziqtest.ui.base

import com.aires.murilo.vuziqtest.data.DataManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BasePresenter<V : MvpView> @Inject constructor(val dataManager: DataManager, val mCompositeDisposable: CompositeDisposable) : MvpPresenter<V>{


    private var mMvpView : V? = null


    override fun onAttach(mvpView: V) {
        mMvpView = mvpView
    }

    override fun onDetach() {
        mCompositeDisposable.dispose()
        mMvpView = null
    }

    fun isViewAttached(): Boolean {
        return mMvpView != null
    }

    fun getMvpView(): V? {
        return mMvpView
    }

    override fun onViewReady() {

    }
}