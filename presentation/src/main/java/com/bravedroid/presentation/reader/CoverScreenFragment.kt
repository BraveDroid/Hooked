package com.bravedroid.presentation.reader

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bravedroid.domain.NoInternetResponseError
import com.bravedroid.domain.Story
import com.bravedroid.domain.SubmitUiModel
import com.bravedroid.presentation.databinding.LayoutCoverScreenFragmentBinding
import com.bravedroid.usecases.reader.Reader

class CoverScreenFragment : Fragment() {

    private var reader: Reader? = null
    private lateinit var binding: LayoutCoverScreenFragmentBinding

    lateinit var vm: CoverScreenVM
    private var listener: Listener? = null

    fun injectReader(reader: Reader) {
        this.reader = reader
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onDetach() {
        listener = null
        reader = null
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LayoutCoverScreenFragmentBinding.inflate(inflater);
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = CoverScreenVM.ViewModelFactory(reader!!)
        vm = ViewModelProviders.of(this, factory).get(CoverScreenVM::class.java)
        binding.vm = vm
        binding.listener = listener
    }


    override fun onResume() {
        super.onResume()
        vm.model.observe(this, Observer { model: SubmitUiModel<Story>? ->
            if (model?.responseState == SubmitUiModel.ResponseState.ERROR) {
                when (model.responseError) {
                    is NoInternetResponseError -> {
                        Toast.makeText(listener as Context, "No internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    interface Listener {
        fun onInitiateStory(storyId: String)
    }

    companion object {
        val TAG = CoverScreenFragment::class.java.simpleName
    }
}
