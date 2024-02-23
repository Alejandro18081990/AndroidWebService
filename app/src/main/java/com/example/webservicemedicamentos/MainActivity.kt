package com.example.webservicemedicamentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                add<FragmentRecyclerMedicamento>(R.id.fragment_container_view)
            }
        }
    }
}