package com.example.mydata3itd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val images: IntArray = intArrayOf(
            R.drawable.ob_habit,
            R.drawable.ob_notif,
            R.drawable.ob_progress
        )

        val viewPager: ViewPager = findViewById<View>(R.id.pager) as ViewPager

        val adapter: PagerAdapter = ImagePageAdapter(this, images)

        viewPager.adapter = adapter
    }
}