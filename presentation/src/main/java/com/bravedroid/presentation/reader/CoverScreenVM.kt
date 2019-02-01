package com.bravedroid.presentation.reader

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
   //  val data: LiveData<Story> = reader.getStory()

    var data: MutableLiveData<Story> = MutableLiveData()

    init {
        data.postValue(
            Story(
                1,
                "title Fat7iz",
                "description my man",
                "https://d255esdrn735hr.cloudfront.net/sites/default/files/bookretailers/V13309_Low.png"
            )
        )
    }


    @get:Bindable
    var title = "This is the title"
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var description = """This is the description.
        When a callback is used in an expression,
        data binding automatically creates the necessary listener
        and registers it for the event. When the view fires the event,
         data binding evaluates the given expression.
         As in regular binding expressions, you still get null and thread
         safety of data binding while these listener expressions are being evaluated.
    """
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @get:Bindable
    var imageCoverUrl =
        "https://scontent-cdt1-1.xx.fbcdn.net/v/t1.0-9/51094050_777735275925201_2742110278014468096_n.jpg?_nc_cat=108&_nc_ht=scontent-cdt1-1.xx&oh=df9f8c1812d4e5a5fe1501b1d3fe21a8&oe=5CFEA96B"
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

    fun onArrowButtonClick() {
        Log.v("TAG", "onArrowButtonClick")
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
                changeCoverImageVisible(true)
                return false
            }
        }
    }

    private fun changeCoverImageVisible(isVisible: Boolean) {
        isImageCoverVisible = isVisible
    }

    class ViewModelFactory(private val reader: Reader) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CoverScreenVM(reader) as T
        }
    }
}
