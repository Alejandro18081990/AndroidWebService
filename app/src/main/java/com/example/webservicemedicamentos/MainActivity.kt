package com.example.webservicemedicamentos

import android.Manifest.permission.READ_MEDIA_IMAGES
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.webservicemedicamentos.ui.recyclerMedicamentos.FragmentRecyclerMedicamento


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FragmentRecyclerMedicamento>(R.id.nav_host_fragment_content_main)
            }
        }
    }
}