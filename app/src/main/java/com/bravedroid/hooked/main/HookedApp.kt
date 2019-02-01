package com.bravedroid.hooked.main

import android.app.Activity
import android.app.Application
import com.bravedroid.data.repository.RepositoryImpl
import com.bravedroid.usecases.reader.Reader
import com.bravedroid.usecases.repository.Repository

class HookedApp : Application() {
    private val repositoryImpl: Repository = RepositoryImpl()
    private val reader: Reader = Reader(repositoryImpl)

    override fun onCreate() {
        super.onCreate()
    }

    fun injectReader(): Reader = reader

}
