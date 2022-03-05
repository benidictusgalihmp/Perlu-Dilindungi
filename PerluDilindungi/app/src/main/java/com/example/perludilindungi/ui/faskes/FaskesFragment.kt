package com.example.perludilindungi.ui.faskes

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.FaskesListAdapter
import com.example.perludilindungi.api.controller.CityController
import com.example.perludilindungi.api.controller.FaskesController
import com.example.perludilindungi.api.controller.ProvinceController
import com.example.perludilindungi.databinding.FragmentFaskesBinding
import com.example.perludilindungi.model.Province.ProvinceData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class FaskesFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private val faskesViewModel: FaskesViewModel by activityViewModels()

    private var boolProvSpinner = true
    private var boolCitySpinner = true
    private var permissionGranted = false

    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private var longitude: Double? = null
    private var latitude: Double? = null

    private var _binding: FragmentFaskesBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_faskes, container, false)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            permissionGranted = when {
                permissions.getOrDefault(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    false
                ) -> {
                    // Precise location access granted.
                    getLocation()
                    true
                }
                permissions.getOrDefault(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    false
                ) -> {
                    // Only approximate location access granted.
                    getLocation()
                    true
                }
                else -> {
                    // No location access granted.
                    false
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        val searchButton: Button = rootView.findViewById(R.id.btn_search_faskes)
        searchButton.setOnClickListener {
            if (faskesViewModel.provinceSelect.value != null && faskesViewModel.citySelect.value != null) {
                if (!permissionGranted) {
                    locationPermissionRequest.launch(
                        arrayOf(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )

                    return@setOnClickListener
                }

                if (latitude == null || longitude == null){
                    Toast.makeText(activity, "Location unknown", Toast.LENGTH_LONG).show()
                    getLocation()
                    return@setOnClickListener
                }

                val city = faskesViewModel.cities.value?.get(faskesViewModel.citySelect.value!!)
                val prov = faskesViewModel.provinces.value?.get(faskesViewModel.provinceSelect.value!!)
                if (city != null && prov != null) {
                    FaskesController(faskesViewModel).start(prov.key!!, city.key!!)
                }
            }
        }

        faskesViewModel.provinceSelect.observe(requireActivity(), Observer {
            if (it != null) {
                val prov: ProvinceData? = faskesViewModel.provinces.value?.get(it)
                if (prov != null) {
                    CityController(faskesViewModel).start(prov.key!!)
                }
            } else {
                faskesViewModel.cities.value = null
            }
        })

        faskesViewModel.citiesFetch.observe(requireActivity(), Observer {
            val spinnerLoadingText: TextView? = view?.findViewById(R.id.spinner_cities)
            if (it) {
                spinnerLoadingText?.text = "Loading"
            } else {
                spinnerLoadingText?.text = ""
            }
        })

        faskesViewModel.provincesFetch.observe(requireActivity(), Observer {
            val spinnerLoadingText: TextView = rootView.findViewById(R.id.spinner_provinces)
            if (it) {
                spinnerLoadingText.text = "Loading"
            } else {
                spinnerLoadingText.text = ""
            }
        })

        activity?.let {
            faskesViewModel.provinces.observe(it, Observer {
                val provinceSpinner: Spinner = rootView.findViewById(R.id.spinner_provinces)
                val array: ArrayList<String> = ArrayList();
                if (it != null) {
                    array.add("Pilih Provinsi")
                    for (prov in it) {
                        array.add(prov.value!!)
                    }
                } else {
                    Log.i("ViewModel:Provinces", "Null value")
                }
                if (activity != null){
                    val adapterTemp: ArrayAdapter<String> = ArrayAdapter<String>(
                        requireActivity(),
                        android.R.layout.simple_spinner_item,
                        array.toTypedArray()
                    )
                    adapterTemp.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                    provinceSpinner.adapter = adapterTemp
                }

            })
        }

        faskesViewModel.cities.observe(requireActivity(), Observer {
            val citySpinner: Spinner = rootView.findViewById(R.id.spinner_cities)
            val array: ArrayList<String> = ArrayList();
            if (it != null) {
                array.add("Pilih Kota")
                for (city in it) {
                    array.add(city.value!!)
                }
            } else {
                Log.i("ViewModel:Cities", "Null value")
            }

            val adapterTemp: ArrayAdapter<String> = ArrayAdapter<String>(
                requireActivity(),
                android.R.layout.simple_spinner_item,
                array.toTypedArray()
            )
            adapterTemp.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            citySpinner.adapter = adapterTemp
        })

        faskesViewModel.faskesesFetch.observe(requireActivity(), Observer {
            if (it) {
                searchButton.text = "Please Wait..."
            } else {
                searchButton.text = "Search"
            }
        })

        faskesViewModel.faskeses.observe(requireActivity(), Observer {
            if (it != null) {
                val faskesListAdapter = FaskesListAdapter(context as Activity, it)
                val faskesListView : ListView? = view?.findViewById(R.id.listview_faskes)
                faskesListView?.adapter = faskesListAdapter
            }


        })

        setupProvinceSpinner(rootView)
        setupCitySpinner(rootView)

//        parentFragmentManager.beginTransaction().replace(
//            R.id.faskesListFragmentReplace, FaskesListFragment()).commit()

        return rootView
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location : Location? ->
            location?.let {
                longitude = it.longitude
                latitude = it.latitude
            }
        }
    }

    private fun setupCitySpinner(rootView: View) {
        val citySpinner: Spinner = rootView.findViewById(R.id.spinner_cities)

        val testArray: Array<String> = arrayOf()

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, testArray)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        citySpinner.adapter = adapter
        citySpinner.onItemSelectedListener = this
    }

    private fun setupProvinceSpinner(rootView: View) {
        val provinceSpinner: Spinner = rootView.findViewById(R.id.spinner_provinces)

        val testArray: Array<String> = arrayOf()
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, testArray)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        provinceSpinner.adapter = adapter
        provinceSpinner.onItemSelectedListener = this

        ProvinceController(faskesViewModel).start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}