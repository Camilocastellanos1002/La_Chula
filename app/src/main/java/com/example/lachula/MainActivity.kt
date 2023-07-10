package com.example.lachula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //para el Action Bar que se desarrollo sea visible
        setActionBar(findViewById(R.id.ToolbarMain))
        configNav()
    }
    fun  configNav(){
        //la navegacion que se va a realizar que se configure atravez del navcontroller heredado del menu bnvmenu como padre, y la navegacion se hara encontrando
        // el nav controller y donde se cargaran todos los fragmentos en fragcontent
        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragContent))
    }

}




