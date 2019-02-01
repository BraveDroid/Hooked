package com.bravedroid.presentation.reader

import android.graphics.drawable.Drawable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class CoverScreenVM : BaseObservable() {

    @get:Bindable
    var title = "This is the title"
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var description = """This is the description.
        This is the description. This is the description.
        This is the description.
        This is the description.
        This is the description.
        This is the description.
        This is the description.
        This is the description.
    """
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @get:Bindable
    var imageCoverUrl =
        "https://scd.france24.com/en/files/imagecache/hermes_infographie_vignette_home/article/image/mbs-anticorruption.jpg"
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageCoverUrl)
        }

    @get:Bindable
    var isImageCoverVisible = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageCoverVisible)
        }

    fun onArrowButtonClick() {}


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
                changeCoverImageVisible(true)
                return false
            }
        }
    }

    private fun changeCoverImageVisible(isVisible: Boolean) {
        isImageCoverVisible = isVisible
    }
}
