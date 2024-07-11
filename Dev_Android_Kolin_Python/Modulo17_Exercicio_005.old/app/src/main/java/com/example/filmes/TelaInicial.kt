package com.example.filmes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.filmes.databinding.ActivityTelaInicialBinding

class TelaInicial : AppCompatActivity() {

    private lateinit var binding: ActivityTelaInicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Espera 3 segundos antes de abrir a tela do app
        Handler(Looper.getMainLooper()).postDelayed({
            abrirApp()
        }, 3000)
    }

    private fun abrirApp() {
        val intent = Intent(this@TelaInicial, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}