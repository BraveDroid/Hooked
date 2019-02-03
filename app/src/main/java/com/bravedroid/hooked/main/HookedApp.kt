package com.bravedroid.hooked.main

import android.app.Application
import com.bravedroid.data.repository.RepositoryImpl
import com.bravedroid.usecases.reader.Reader
import com.bravedroid.usecases.repository.Repository

class HookedApp : Application() {
    private lateinit var repositoryImpl: Repository
    private lateinit var reader: Reader

    override fun onCreate() {
        super.onCreate()
        repositoryImpl = RepositoryImpl(this)
        reader = Reader(repositoryImpl)
    }

    fun injectReader(): Reader = reader

}
