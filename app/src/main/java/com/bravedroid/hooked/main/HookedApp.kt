package com.bravedroid.hooked.main

import android.app.Application
import android.util.Log
import androidx.annotation.Nullable
import com.bravedroid.data.repository.RepositoryImpl
import com.bravedroid.hooked.BuildConfig
import com.bravedroid.usecases.reader.Reader
import com.bravedroid.usecases.repository.Repository
import timber.log.Timber

class HookedApp : Application() {
    private lateinit var repositoryImpl: Repository
    private lateinit var reader: Reader

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        else
            Timber.plant(TimberCrashlyticsTree())

        repositoryImpl = RepositoryImpl(this)
        reader = Reader(repositoryImpl)
    }

    fun getReader(): Reader = reader


    private class TimberCrashlyticsTree : Timber.Tree() {
        override fun isLoggable(tag: String?, priority: Int): Boolean {
            return priority == Log.ERROR
        }

        override fun log(priority: Int, @Nullable tag: String?, @Nullable message: String, @Nullable t: Throwable?) {
            if (t != null) {
                //Crashlytics.logException(t)
            }
        }
    }
}
