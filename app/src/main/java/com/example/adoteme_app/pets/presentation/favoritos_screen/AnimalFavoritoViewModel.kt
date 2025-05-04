package com.example.adoteme_app.pets.presentation.favoritos_screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.AnimalApiService
import com.example.adoteme_app.model.AnimalFavoritoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class AnimalFavoritoViewModel(
    private val animalService: AnimalApiService,
) : ViewModel() {

    private val _favoritos = MutableStateFlow<List<AnimalFavoritoDto>>(emptyList())
    val favoritos: StateFlow<List<AnimalFavoritoDto>> = _favoritos.asStateFlow()

    suspend fun carregarFavoritos(idAdotante: Long) {
        try {
            val response = animalService.getFavoritosByAdotanteId(idAdotante)
            _favoritos.value = response.animaisfavoritos
        } catch (e: Exception) {
            Log.e("AnimalFavoritoVM", "Erro ao carregar favoritos", e)
        }
    }
}
