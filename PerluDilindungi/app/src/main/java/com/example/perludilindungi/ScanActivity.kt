package com.example.perludilindungi

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Bundle
import android.os.HandlerThread
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.perludilindungi.model.ScanPostBody
import com.example.perludilindungi.repository.Repository
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_scan.*
import org.w3c.dom.Text

private const val CAMERA_REQUEST_CODE = 101

class ScanActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var codeScanner: CodeScanner
    private lateinit var viewModel: MainViewModel

    private lateinit var location: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private lateinit var sensorManager: SensorManager
    private var temperature: Sensor? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        location = Location("")
        getLocationUpdates()

        setupPermissions()
        codeScanner()
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        tvTemperature.setText("${p0!!.values[0]}℃")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    private fun getLocationUpdates() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval = 500
            smallestDisplacement = 170f
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult ?: return
                if (locationResult.locations.isNotEmpty()) {
                    location =
                        locationResult.lastLocation
                }
            }
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 44)
            return
        }

        val handlerThread = HandlerThread("locationThread")
        handlerThread.start()

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            handlerThread.looper
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    fun getLocation(): Location {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return location
        } else {
            AlertDialog.Builder(this)
                .setTitle("Location Permission Needed")
                .setMessage("This app needs the Location permission, please accept to use location functionality")
                .setPositiveButton("OK") { _, _ ->
                    //Prompt the user once explanation has been shown
                    makeRequestLocation()
                }
                .create()
                .show()
        }
        return location
    }

    private fun checkIn(code: String) {

        val l = getLocation()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val postBody = ScanPostBody(code, l.latitude.toInt(), l.longitude.toInt())
        viewModel.scanPost(postBody)
        viewModel.scanResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                if (response.body()?.data?.userStatus == "green" || response.body()?.data?.userStatus == "yellow") {
                    scanText.text = "Berhasil"
                    scanReason.text = ""
                    scanStatus.setImageResource(R.drawable.berhasil_scan)
                } else if (response.body()?.data?.userStatus == "red" || response.body()?.data?.userStatus == "black") {
                    scanText.text = "Gagal"
                    scanReason.text = response.body()!!.data.reason
                    scanStatus.setImageResource(R.drawable.gagal_scan)
                }
            } else {
                Toast.makeText(this, response.errorBody().toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun codeScanner() {
        codeScanner = CodeScanner(this, scanner_view)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    checkIn(it.text)
                }
            }
            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Scan", "Camera initialization error: ${it.message}")
                }
            }
        }

        scanner_view.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_NORMAL)
        codeScanner.startPreview()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        codeScanner.releaseResources()
        stopLocationUpdates()
    }

    private fun setupPermissions() {
        val cameraPermission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA)

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            makeRequestCamera()
        }
    }

    private fun makeRequestCamera() {
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    private fun makeRequestLocation() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            44
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You need camera permission to be able to use this app!", Toast.LENGTH_SHORT).show()
                } else {
                    // success
                }
            }
        }
    }
}