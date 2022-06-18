package com.example.aula14_2combustivelmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    //tem que declarar duas variáveis, onde uma é mutavel e a outra não
    // o underline é uma boa prática para diferenciar a variável da variavel mutavel
    private var _gastoGasolina: MutableLiveData<Double> = MutableLiveData() // vai deixar essa variavel privada para a view model, para não deixar a view modificar ela // Aqui tem métodos para mudar a variável
    val gastoGasolina: LiveData<Double> // aqui tem métodos para observar a variável
    get() = _gastoGasolina

    private var _gastoAlcool: MutableLiveData<Double> = MutableLiveData()
    val gastoAlcool: LiveData<Double>
    get() = _gastoAlcool

    // vai recuperar os métodos
    fun calcularGasolina(etDistancia: Double, etKm: Double, etPrecoG: Double) {
        val consumoGasolina = calculateConsumption(etDistancia, etKm, etPrecoG)
        _gastoGasolina.postValue(consumoGasolina) //o .postValue vai postar o valor da variável
    }

    fun calcularAlcool(etDistancia: Double, etKm: Double, etPrecoA: Double) {
        val consumoAlcool = calculateConsumption(etDistancia, etKm, etPrecoA)
        _gastoAlcool.postValue(consumoAlcool) // o .postValue é um método do MutableLiveData, pois não dá para atribuir o valor diretamente na variável
    }

    private fun calculateConsumption(

        etDistanciaA: Double,
        valorCombustivel: Double,
        etKmK: Double

    ): Double { return (etDistanciaA * etKmK ) / valorCombustivel  // Vai calcular a distancia vezes o kilometro e dividir pelo valor do combustível
    }
}