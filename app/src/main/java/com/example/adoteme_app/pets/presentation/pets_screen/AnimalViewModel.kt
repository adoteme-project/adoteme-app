package com.example.adoteme_app.pets.presentation.pets_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.AnimalApiService
import com.example.adoteme_app.model.AnimalResponse
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

    private fun carregarAnimais() {
        viewModelScope.launch {
            try {
                val resposta = animalApiService.getTodosAnimais()
                _animais.value = resposta
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Erro ao carregar animais: ${e.message}")
            }
        }
    }

    fun carregarAnimalPorId(id: Int) {
        viewModelScope.launch {
            try {
                val response = animalApiService.getAnimalById(id)
                _animal.value = response
            } catch (e: Exception) {
                Log.e("AnimalViewModel", "Erro ao carregar o animal: ${e.message}")
            }
        }
    }
}
