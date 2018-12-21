package com.jlccaires.norrisfacts.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.jlccaires.norrisfacts.R
import kotlinx.android.synthetic.main.activity_main.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    @AfterViews
    fun init() {
        setSupportActionBar(toolBar)
        setupActionBarWithNavController(navHost.findNavController())
    }

    override fun onSupportNavigateUp() = navHost.findNavController().navigateUp()
}
