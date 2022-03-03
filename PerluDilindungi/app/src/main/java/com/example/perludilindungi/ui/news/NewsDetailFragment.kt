package com.example.perludilindungi.ui.news

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.perludilindungi.databinding.FragmentNewsDetailBinding
import com.example.perludilindungi.model.News

private const val ARG_NEWS_URL = "ARG_NEWS_URL"

class NewsDetailFragment: Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Log.d("Test", "halo1")
        return root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Test", "halo2")
        Log.d("Binding", _binding.toString())
        Log.d("Binding", _binding?.newsDetailWebView.toString())
        arguments?.let {
            _binding?.newsDetailWebView?.settings?.javaScriptEnabled = true
            _binding?.newsDetailWebView?.settings?.safeBrowsingEnabled = false
            _binding?.newsDetailWebView?.loadUrl(ARG_NEWS_URL)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(news: News) =
            NewsDetailFragment().apply {
                Log.d("Test", "halo")
                arguments = Bundle().apply {
                    putString(ARG_NEWS_URL, news.enclosure._url)
                }
            }
    }
}