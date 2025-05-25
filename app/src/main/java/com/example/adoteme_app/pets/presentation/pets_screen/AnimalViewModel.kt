package com.example.adoteme_app.pets.presentation.pets_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.AnimalApiService
import com.example.adoteme_app.model.AnimalResponse
import com.example.adoteme_app.ui.components.categoriaParaPersonalidade
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val animalApiService: AnimalApiService
) : ViewModel() {

    private val _animais = MutableStateFlow<List<AnimalResponse>>(emptyList())
    val animais: StateFlow<List<AnimalResponse>> = _animais

    private val _animal = MutableStateFlow<AnimalResponse?>(null)
    val animal: StateFlow<AnimalResponse?> = _animal

    init {
        carregarAnimais()
    }

    fun carregarAnimais() {
        Log.d("ViewModel", "Iniciando carregamento dos animais")
        viewModelScope.launch {
            try {
                val resposta = animalApiService.getTodosAnimais()
                Log.d("ViewModel", "Animais carregados: ${resposta.size}")
                _animais.value = resposta
            } catch (e: Exception) {
                Log.e("ViewModel", "Erro ao carregar animais", e)
            }
        }
    }


    fun carregarAnimalPorId(id: Long) {
        viewModelScope.launch {
            try {
                val response = animalApiService.getAnimalById(id)
                _animal.value = response
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Erro ao carregar o animal: ${e.message}")
            }
        }
    }

    fun filtrarPorCategoria(categoriaNome: String) {
        val seletor = categoriaParaPersonalidade[categoriaNome]
        seletor?.let { seletorFunc ->
            val listaFiltrada = _animais.value
                .sortedByDescending { seletorFunc(it.personalidade) }
            _animais.value = listaFiltrada
        }
    }

}
