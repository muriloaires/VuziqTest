package com.aires.murilo.vuziqtest.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import com.aires.murilo.vuziqtest.di.component.ActivityComponent

abstract class BaseFragment : androidx.fragment.app.Fragment(), MvpView {

    private var mActivity: BaseActivity? = null
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.mActivity = activity
            this.mActivity!!.onFragmentAttached()
        }
    }


    override fun onError(@StringRes resId: Int) {
        if (mActivity != null) {
            mActivity!!.onError(resId)
        }
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }


    fun getActivityComponent(): ActivityComponent? {
        return if (mActivity != null) {
            mActivity!!.getActivityComponent()
        } else null
    }

    fun getBaseActivity(): BaseActivity? {
        return mActivity
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}