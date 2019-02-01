package com.bravedroid.presentation.reader

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bravedroid.domain.Story
import com.bravedroid.presentation.base.BaseViewModelObservable
import com.bravedroid.usecases.reader.Reader
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class CoverScreenVM(val reader: Reader) : BaseViewModelObservable() {
    val data: LiveData<Story> = reader.getStory()

    @get:Bindable
    var isImageCoverVisible = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageCoverVisible)
        }

    fun getRequestListener(): RequestListener<Drawable> {
        return object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                isImageCoverVisible = true
                return false
            }
        }
    }

    class ViewModelFactory(private val reader: Reader) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CoverScreenVM(reader) as T
        }
    }
}
