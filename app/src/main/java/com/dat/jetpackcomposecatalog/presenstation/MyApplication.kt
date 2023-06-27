package com.dat.jetpackcomposecatalog.presenstation

import android.app.Application
import com.dat.jetpackcomposecatalog.BuildConfig
import com.dat.jetpackcomposecatalog.core.common.Constants
import com.dat.jetpackcomposecatalog.core.util.InternetUtil
import com.dat.jetpackcomposecatalog.core.util.LogsUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)
        if (BuildConfig.FLAVOR == Constants.FLAVOR_DEVELOP) {
            Timber.plant(LogsUtil())
        }
    }
}
