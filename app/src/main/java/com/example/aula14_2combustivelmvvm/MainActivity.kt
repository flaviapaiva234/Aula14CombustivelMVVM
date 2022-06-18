package com.example.aula14_2combustivelmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aula14_2combustivelmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModelProvider garante que a vielModel vai ficar associada ao ciclo de vida da
        // activity ou fragment,  começa quando a activity iniciar e termina quando a activity terminar
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setUpListeners()
        setUpObservers()

    }

    // Pegar todos os botões a adicionar clicks a eles

    private fun setUpListeners() {
        binding.apply {
            btnResultado.setOnClickListener {
                viewModel.calcularGasolina(
                    etDistancia = etDistanciaViajem.text.toString().toDouble(),
                    etKm = etKmPorLitro.text.toString().toDouble(),
                    etPrecoG = etPrecoGasolina.text.toString().toDouble()
                )
                viewModel.calcularAlcool(
                    etDistancia = etDistanciaViajem.text.toString().toDouble(),
                    etKm = etKmPorLitro.text.toString().toDouble(),
                    etPrecoA = etPrecoAlcool.text.toString().toDouble()
                )
            }
        }
    }

    private fun setUpObservers() {
        viewModel.apply {

            //também tem que falar o contexto de quem está observando(que é essam mesma activity(this@MainActivity), chamar o Observer
            //e o retorno no it é um Double //por exemplo: se lá na activity MainViewmodel os pontos fossem String, o it retornaria String) // Aqui está recebendo o tipo da variável que foi declarada anteriormente, o valor dela
            gastoGasolina.observe(this@MainActivity, Observer {
                binding.tvResultadoGasolina.text = it.toString() // vai receber o it que é o valore colocar o .toString pq era Double
            })
            gastoAlcool.observe(this@MainActivity, Observer {
                binding.tvResultadoAlcool.text = it.toString()
            })
        }
    }
}