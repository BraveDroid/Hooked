package com.bravedroid.hooked.reader

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bravedroid.hooked.main.HookedApp
import com.bravedroid.presentation.R
import com.bravedroid.presentation.databinding.LayoutReaderActivityBinding
import com.bravedroid.presentation.reader.CoverScreenFragment

class ReaderActivity: AppCompatActivity() {
    private lateinit var binding: LayoutReaderActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app =application as HookedApp
        val reader= app.inject()

        binding = DataBindingUtil.setContentView(this, R.layout.layout_reader_activity)

        val isCreatedFirstTime = savedInstanceState == null
        if (isCreatedFirstTime) {
            val coverScreenFragment = CoverScreenFragment()
            coverScreenFragment.reader=reader
            placeFragment(coverScreenFragment)
        }
    }

    private fun placeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(binding.readerFragmentContainer.id, fragment, CoverScreenFragment.TAG)
            .commit()
    }

}
