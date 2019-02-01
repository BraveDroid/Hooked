package com.bravedroid.hooked.reader

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bravedroid.hooked.main.HookedApp
import com.bravedroid.presentation.R
import com.bravedroid.presentation.databinding.LayoutReaderActivityBinding
import com.bravedroid.presentation.reader.CoverScreenFragment
import com.bravedroid.presentation.reader.ReaderScreenFragment
import com.bravedroid.usecases.reader.Reader

class ReaderActivity : AppCompatActivity(), CoverScreenFragment.Listener {
    private lateinit var binding: LayoutReaderActivityBinding
    private lateinit var reader: Reader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reader = (application as HookedApp).injectReader()

        binding = DataBindingUtil.setContentView(this, R.layout.layout_reader_activity)

        val isCreatedFirstTime = savedInstanceState == null
        if (isCreatedFirstTime) {
            val coverScreenFragment = CoverScreenFragment()
            coverScreenFragment.injectReader(reader)
            placeFragment(coverScreenFragment, CoverScreenFragment.TAG)
        }
    }

    override fun onInitiateStory(storyId: Int) {
        Log.v("TAG", "onInitiateStory  $storyId")

        val readerScreenFragment = ReaderScreenFragment()
        readerScreenFragment.injectReader(reader)
        placeFragment(readerScreenFragment, ReaderScreenFragment.TAG)
    }

    private fun placeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .add(binding.readerFragmentContainer.id, fragment, tag)
                .commit()
    }
}
