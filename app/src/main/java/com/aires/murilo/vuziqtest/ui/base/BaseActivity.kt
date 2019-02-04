package com.aires.murilo.vuziqtest.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aires.murilo.vuziqtest.R
import com.aires.murilo.vuziqtest.app.VuziqTestApp
import com.aires.murilo.vuziqtest.di.component.ActivityComponent
import com.aires.murilo.vuziqtest.di.component.DaggerActivityComponent
import com.aires.murilo.vuziqtest.di.module.ActivityModule

abstract class BaseActivity : AppCompatActivity(), MvpView, BaseFragment.Callback {

    private lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as VuziqTestApp).mApplicationComponent)
            .build()
    }

    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }


    override fun onError(resId: Int) {
        showMessage(getString(resId))
    }

    private fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.something_wrong_occurred), Toast.LENGTH_SHORT).show()
        }
    }


    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }
}