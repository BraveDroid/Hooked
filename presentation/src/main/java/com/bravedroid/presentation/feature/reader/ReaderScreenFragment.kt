package com.bravedroid.presentation.feature.reader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bravedroid.presentation.databinding.LayoutReaderScreenFragmentBinding
import com.bravedroid.usecases.reader.Reader

class ReaderScreenFragment : Fragment() {

    private lateinit var reader: Reader
    private lateinit var binding: LayoutReaderScreenFragmentBinding

    lateinit var vm: ReaderScreenVM

    var listener: Listener? = object : Listener {
        override fun onNextClicked() {
            (binding.recyclerView.adapter as MessageListAdapter).updateLastIndex()
            binding.recyclerView.scrollToPosition(binding.recyclerView.adapter!!.itemCount - 1)
        }
    }

    fun injectReader(reader: Reader) {
        this.reader = reader
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LayoutReaderScreenFragmentBinding.inflate(inflater);
        binding.setLifecycleOwner(this)
        binding.listener = listener
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ReaderScreenVM.ViewModelFactory(reader)
        vm = ViewModelProviders.of(this, factory).get(ReaderScreenVM::class.java)
        binding.vm = vm
    }


    interface Listener {
        fun onNextClicked()
    }

    companion object {
        val TAG = ReaderScreenFragment::class.java.simpleName
    }
}

