package com.example.opscpoe

import RewardsFragment
import YourInitialFragment
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private val CAMERA_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        // Initialize ImageViews
        val rewardsImageView: ImageView = findViewById(R.id.imageView)
        val mapImageView: ImageView = findViewById(R.id.imageView3)
        val homeImageView: ImageView = findViewById(R.id.imageView5)
        val observationsImageView: ImageView = findViewById(R.id.imageView6)
        val settingsImageView: ImageView = findViewById(R.id.imageView7)

        // Load the initial fragment
        loadFragment(YourInitialFragment())

        // Set onClickListener for Rewards
        rewardsImageView.setOnClickListener {
            loadFragment(RewardsFragment())
        }

        // Set onClickListener for Map
        mapImageView.setOnClickListener {
            loadFragment(MapFragment())
        }

        // Set onClickListener for Camera
        homeImageView.setOnClickListener {
          loadFragment(YourInitialFragment())
        }

        // Set onClickListener for Observations
        observationsImageView.setOnClickListener {
            loadFragment(ObservationsFragment())
        }

        // Set onClickListener for Settings
        settingsImageView.setOnClickListener {
            loadFragment(SettingsFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Load the given fragment into the fragment_container
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            }
        }
    }
}
