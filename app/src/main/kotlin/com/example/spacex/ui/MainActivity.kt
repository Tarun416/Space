package com.example.spacex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.domain.model.ItemDomain
import com.example.spacex.R
import com.example.spacex.ui.details.DetailsFragment
import com.example.spacex.ui.list.ListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ListFragment.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, ListFragment.newInstance())
            .commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

    override fun onPicClick(item: ItemDomain) {
        val detailsFragment = DetailsFragment()
        val args = Bundle()
        args.putString("title", item.title)
        args.putString("explanation", item.explanation)
        args.putString("url", item.url)
        detailsFragment.arguments = args
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .add(R.id.content, detailsFragment)
            .addToBackStack("detail")
            .commit()
    }
}