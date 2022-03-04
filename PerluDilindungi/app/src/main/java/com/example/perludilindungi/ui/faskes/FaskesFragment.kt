package com.example.perludilindungi.ui.faskes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.MainViewModelFactory
import com.example.perludilindungi.R
import com.example.perludilindungi.databinding.FragmentFaskesBinding
import com.example.perludilindungi.repository.Repository

class FaskesFragment : Fragment() {

    private lateinit var faskesViewModel: FaskesViewModel

    private var _binding: FragmentFaskesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaskesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        Log.d("##########", "MASUK BEFORE VIEW MODEL")
        faskesViewModel = ViewModelProvider(this, viewModelFactory).get(FaskesViewModel::class.java)
        Log.d("##########", "MASUK AFTER VIEW MODEL")

        faskesViewModel.provResponse.observe(requireActivity(), Observer {
            Log.d("VALUE OF IT", it.toString())
            if (it != null) {
                val spinnerProv : Spinner = root.findViewById(R.id.spinner_provinces)
                val arrProvinces : ArrayList<String> = ArrayList()
                it.results.forEach {
                    arrProvinces.add(it.value)
                }
                val arrayAdapterProv : ArrayAdapter<String> = ArrayAdapter(requireActivity(), R.layout.fragment_faskes, arrProvinces.toTypedArray())
                spinnerProv.adapter = arrayAdapterProv
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}