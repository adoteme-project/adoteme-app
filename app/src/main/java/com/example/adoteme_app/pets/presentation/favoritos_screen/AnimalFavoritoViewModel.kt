package com.example.adoteme_app.pets.presentation.favoritos_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.AnimalApiService
import com.example.adoteme_app.model.AnimalFavoritoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimalFavoritoViewModel(
    private val animalService: AnimalApiService
) : ViewModel() {

    private val _favoritos = MutableStateFlow<List<AnimalFavoritoDto>>(emptyList())
    val favoritos: StateFlow<List<AnimalFavoritoDto>> = _favoritos

    fun carregarFavoritos(adotanteId: Long) {
        viewModelScope.launch {
            try {
                val response = animalService.getFavoritosByAdotanteId(adotanteId)
                _favoritos.value = response.animaisfavoritos
            } catch (e: Exception) {
                Log.e("AnimalFavoritoVM", "Erro ao carregar favoritos", e)
            }
        }
    }
}
