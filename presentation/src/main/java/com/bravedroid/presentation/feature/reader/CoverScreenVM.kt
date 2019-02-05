package com.bravedroid.presentation.feature.reader

import android.graphics.drawable.Drawable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.*
import com.bravedroid.domain.model.Story
import com.bravedroid.presentation.base.BaseViewModelObservable
import com.bravedroid.usecases.model.SubmitUiModel
import com.bravedroid.usecases.reader.Reader
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class CoverScreenVM(reader: Reader) : BaseViewModelObservable() {
    private val idStoryLiveData = MutableLiveData<String>()

    val model: LiveData<SubmitUiModel<Story>> = Transformations.switchMap(idStoryLiveData) {
        reader.getStory(it)
    }

    fun loadStory(idStory: String) {
        idStoryLiveData.value = idStory
    }

    @get:Bindable
    var isImageCoverVisible = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageCoverVisible)
        }

    fun onRetryClick(idStory: String) = loadStory(idStory)

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
