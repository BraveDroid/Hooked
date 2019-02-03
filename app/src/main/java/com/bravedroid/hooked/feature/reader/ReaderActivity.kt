package com.bravedroid.hooked.feature.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bravedroid.hooked.main.HookedApp
import com.bravedroid.presentation.R
import com.bravedroid.presentation.databinding.LayoutReaderActivityBinding
import com.bravedroid.presentation.feature.reader.CoverScreenFragment
import com.bravedroid.presentation.feature.reader.ReaderScreenFragment
import com.bravedroid.usecases.reader.Reader

private const val STORY_ID = "scavengerhunt"

class ReaderActivity : AppCompatActivity(), CoverScreenFragment.Listener {
    private lateinit var binding: LayoutReaderActivityBinding
    private var reader: Reader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reader = (application as HookedApp).getReader()

        binding = DataBindingUtil.setContentView(this, R.layout.layout_reader_activity)

        val isCreatedFirstTime = savedInstanceState == null
        if (isCreatedFirstTime) {
            val coverScreenFragment = CoverScreenFragment.newInstance(STORY_ID)
            coverScreenFragment.injectReader(reader!!)
            placeFragment(coverScreenFragment, CoverScreenFragment.TAG)
        }

        injectDependencies()
    }

    override fun onDestroy() {
        reader = null
        super.onDestroy()
    }

    private fun injectDependencies() {
        (supportFragmentManager.findFragmentByTag(CoverScreenFragment.TAG) as? CoverScreenFragment)
            ?.injectReader(reader!!)
        (supportFragmentManager.findFragmentByTag(ReaderScreenFragment.TAG) as? ReaderScreenFragment)
            ?.injectReader(
                reader!!
            )
    }

    override fun onInitiateStory(storyId: String) {
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
