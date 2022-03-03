package com.example.perludilindungi.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.MainViewModel
import com.example.perludilindungi.MainViewModelFactory
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.NewsAdapter
import com.example.perludilindungi.databinding.FragmentNewsBinding
import com.example.perludilindungi.databinding.FragmentNewsDetailBinding
import com.example.perludilindungi.model.News
import com.example.perludilindungi.repository.Repository

class NewsFragment : Fragment(R.layout.fragment_news){

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsViewModel: MainViewModel
    private val newsAdapter by lazy { NewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.newsRecyclerView.layoutManager = layoutManager
        binding.newsRecyclerView.adapter = newsAdapter

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        newsViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        newsViewModel.getNews()
        newsViewModel.newsResponse.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful) {
                response.body()?.let { newsAdapter.setNewsData(it.results) }
            } else {
                Toast.makeText(requireActivity(), response.errorBody().toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }

//    override fun onItemClick(news: News) {
//        val fragment: Fragment = NewsDetailFragment.newInstance(news)
//        val transaction = requireActivity().supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.newsFragment, fragment)
//        transaction.commit()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}