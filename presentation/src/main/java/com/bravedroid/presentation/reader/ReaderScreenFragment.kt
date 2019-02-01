package com.bravedroid.presentation.reader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bravedroid.presentation.databinding.LayoutReaderScreenFragmentBinding
import com.bravedroid.usecases.reader.Reader

class ReaderScreenFragment : Fragment() {
    var tagNameFragment: String? = TAG

    private lateinit var reader: Reader
    private lateinit var binding: LayoutReaderScreenFragmentBinding

    lateinit var vm: ReaderScreenVM

    fun injectReader(reader: Reader) {
        this.reader = reader
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LayoutReaderScreenFragmentBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ReaderScreenVM.ViewModelFactory(reader)
        vm = ViewModelProviders.of(this, factory).get(ReaderScreenVM::class.java)
        binding.vm = vm
    }


    companion object {
        val TAG = ReaderScreenFragment::class.java.simpleName
    }
}

