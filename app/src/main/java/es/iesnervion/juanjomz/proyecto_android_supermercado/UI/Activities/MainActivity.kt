package es.iesnervion.juanjomz.proyecto_android_supermercado.UI.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.iesnervion.juanjomz.proyecto_android_supermercado.UI.ViewModels.MainActivityVM
import es.iesnervion.juanjomz.proyecto_android_supermercado.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //ViewModel
    private val viewModel : MainActivityVM by viewModels()
    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.onCreate()//Simula un constructor
    }
}