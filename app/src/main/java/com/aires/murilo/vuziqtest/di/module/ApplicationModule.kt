package com.aires.murilo.vuziqtest.di.module

import android.app.Application
import android.content.Context
import com.aires.murilo.vuziqtest.data.AppDataManager
import com.aires.murilo.vuziqtest.data.DataManager
import com.aires.murilo.vuziqtest.data.db.AppDbHelper
import com.aires.murilo.vuziqtest.data.db.DbHelper
import com.aires.murilo.vuziqtest.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Provides
    fun provideApplication(): Application = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }
}