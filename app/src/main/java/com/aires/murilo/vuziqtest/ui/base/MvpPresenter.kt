package com.aires.murilo.vuziqtest.ui.base

interface MvpPresenter<V : MvpView> {

    /**
     * Attach the MvpView to the Presenter
     */
    fun onAttach(mvpView: V)


    /**
     * Detach the MvpView to the Presenter
     */
    fun onDetach()

    /**
     * Callback to do the initial task when the view is fully created
     */
    fun onViewReady()
}