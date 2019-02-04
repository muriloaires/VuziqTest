package com.aires.murilo.vuziqtest.app

import android.app.Application
import com.aires.murilo.vuziqtest.data.DataManager
import com.aires.murilo.vuziqtest.di.component.ApplicationComponent
import com.aires.murilo.vuziqtest.di.component.DaggerApplicationComponent
import com.aires.murilo.vuziqtest.di.module.ApplicationModule
import javax.inject.Inject

class VuziqTestApp : Application() {

    @Inject
    lateinit var mDataManager: DataManager

    lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        mApplicationComponent.inject(this)
    }
}
