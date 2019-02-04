package com.aires.murilo.vuziqtest.di.component

import android.app.Application
import android.content.Context
import com.aires.murilo.vuziqtest.app.VuziqTestApp
import com.aires.murilo.vuziqtest.data.DataManager
import com.aires.murilo.vuziqtest.di.ApplicationContext
import com.aires.murilo.vuziqtest.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: VuziqTestApp)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun getDataManager(): DataManager
}