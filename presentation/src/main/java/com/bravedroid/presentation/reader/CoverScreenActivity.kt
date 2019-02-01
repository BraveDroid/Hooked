package com.bravedroid.presentation.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bravedroid.presentation.R
import com.bravedroid.presentation.databinding.LayoutReaderActivityBinding

class CoverScreenActivity : AppCompatActivity() {
    private lateinit var binding: LayoutReaderActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.layout_reader_activity)

        val isCreatedFirstTime = savedInstanceState == null
        if (isCreatedFirstTime) {
            val coverScreenFragment = CoverScreenFragment()
            placeFragment(coverScreenFragment)
        }
    }

    private fun placeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .add(binding.readerFragmentContainer.id, fragment, CoverScreenFragment.TAG)
                .commit()
    }

}
