package com.harsh.notesappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.harsh.notesappmvvm.DB.NoteDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var notesDatabase: NoteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        notesDatabase = NoteDatabase.getInstance(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = supportFragmentManager.findFragmentById(R.id.navController)!!.findNavController()
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}