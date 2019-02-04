package com.aires.murilo.vuziqtest.data

import android.content.Context
import com.aires.murilo.vuziqtest.data.db.DbHelper

interface DataManager : DbHelper  {

    fun getAppContext() : Context
}