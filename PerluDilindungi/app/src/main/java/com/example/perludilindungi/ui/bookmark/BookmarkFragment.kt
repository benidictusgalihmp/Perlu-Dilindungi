package com.example.perludilindungi.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.entities.Bookmark
import com.example.perludilindungi.entities.BookmarkDbViewModel

class BookmarkFragment : Fragment() {

    private lateinit var bookmarkViewModel: BookmarkViewModel
    private lateinit var mBookmarkViewModel: BookmarkDbViewModel
    private var _binding: FragmentBookmarkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookmarkViewModel =
            ViewModelProvider(this).get(BookmarkViewModel::class.java)

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Recycler view
        val adapter = BookmarkAdapter()
        val recyclerView = binding.rvBookmark
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Bookmark View Model
        mBookmarkViewModel = ViewModelProvider(this).get(com.example.perludilindungi.entities.BookmarkDbViewModel::class.java)
        mBookmarkViewModel.getMinifiedData.observe(viewLifecycleOwner, Observer { bookmark ->
            adapter.setData(bookmark)
        })

        binding.btnAddBookmark.setOnClickListener {
            val dummyBookmark = Bookmark(
                (0..1000).random(),
                1,
                "748324",
                "Faskes Oke",
                "Jakarta",
                "Jakarta",
                "Jl. Perjuangan No. 7",
                "0",
                "0",
                "(020) 89908431",
                "KLINIK",
                "",
                "Siap Vaksinasi",
                "Somewhere"
            )
            mBookmarkViewModel.insertBookmark(dummyBookmark)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}