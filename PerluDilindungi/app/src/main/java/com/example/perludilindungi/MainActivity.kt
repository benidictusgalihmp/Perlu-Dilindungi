package com.example.perludilindungi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.fragments.BookmarkFragment
import com.example.perludilindungi.fragments.LocationFragment
import com.example.perludilindungi.fragments.NewsFragment
import com.example.perludilindungi.repository.Repository

class MainActivity : AppCompatActivity() {
    private val newsFragment = NewsFragment()
    private val locationFragment = LocationFragment()
    private val bookmarkFragment = BookmarkFragment()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getNews()
        viewModel.myResponse.observe(this, Observer { response ->
            Log.d("Response", response.success.toString())
            Log.d("Response", response.message)
            Log.d("Response", response.count_total.toString())
            Log.d("Response", response.results.toString())
        })
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