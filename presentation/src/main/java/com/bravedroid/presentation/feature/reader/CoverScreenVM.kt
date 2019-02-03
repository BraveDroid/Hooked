package com.bravedroid.presentation.feature.reader

import android.graphics.drawable.Drawable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bravedroid.domain.Story
import com.bravedroid.domain.SubmitUiModel
import com.bravedroid.presentation.base.BaseViewModelObservable
import com.bravedroid.usecases.reader.Reader
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class CoverScreenVM(reader: Reader, storyId: String) : BaseViewModelObservable() {
    var model: LiveData<SubmitUiModel<Story>> = reader.getStory(storyId)

    @get:Bindable
    var isImageCoverVisible = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageCoverVisible)
        }

    fun onRetryClick() {
        //TODO
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

    class ViewModelFactory(private val reader: Reader, private val storyId: String) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CoverScreenVM(reader, storyId) as T
        }
    }
}
