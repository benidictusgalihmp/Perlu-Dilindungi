package com.example.perludilindungi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.perludilindungi.fragments.BookmarkFragment
import com.example.perludilindungi.fragments.LocationFragment
import com.example.perludilindungi.fragments.NewsFragment

class MainActivity : AppCompatActivity() {
    private val newsFragment = NewsFragment()
    private val locationFragment = LocationFragment()
    private val bookmarkFragment = BookmarkFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(newsFragment)

    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}