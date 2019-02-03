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
    private var reader: Reader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reader = (application as HookedApp).injectReader()

        binding = DataBindingUtil.setContentView(this, R.layout.layout_reader_activity)

        val isCreatedFirstTime = savedInstanceState == null
        if (isCreatedFirstTime) {
            val coverScreenFragment = CoverScreenFragment()
            placeFragment(coverScreenFragment, CoverScreenFragment.TAG)
            coverScreenFragment.injectReader(reader!!)
        } else {
            val coverScreenFragment = supportFragmentManager.findFragmentByTag(CoverScreenFragment.TAG)
            if (coverScreenFragment is CoverScreenFragment) coverScreenFragment.injectReader(reader!!)
            val readerScreenFragment = supportFragmentManager.findFragmentByTag(ReaderScreenFragment.TAG)
            if (readerScreenFragment is ReaderScreenFragment) readerScreenFragment.injectReader(reader!!)
        }
    }

    override fun onDestroy() {
        reader = null
        super.onDestroy()
    }

    override fun onInitiateStory(storyId: String) {
        Log.v("TAG", "onInitiateStory  $storyId")

        val readerScreenFragment = ReaderScreenFragment()
        placeFragment(readerScreenFragment, ReaderScreenFragment.TAG)
        readerScreenFragment.injectReader(reader!!)
    }

    private fun placeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(binding.readerFragmentContainer.id, fragment, tag)
            .commit()
    }

}
