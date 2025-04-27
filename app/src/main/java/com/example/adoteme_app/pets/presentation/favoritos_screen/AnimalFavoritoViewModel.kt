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
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val adotanteId: StateFlow<Long> = savedStateHandle.getStateFlow("idUser", 0L)

    private val _favoritos = MutableStateFlow<List<AnimalFavoritoDto>>(emptyList())
    val favoritos: StateFlow<List<AnimalFavoritoDto>> = _favoritos.asStateFlow()

    init {
        viewModelScope.launch {
            adotanteId
                .filter { it != 0L }
                .distinctUntilChanged()
                .collect { id ->
                    carregarFavoritos(id)
                }
        }
    }

    private suspend fun carregarFavoritos(idAdotante: Long) {
        try {
            val response = animalService.getFavoritosByAdotanteId(idAdotante)
            _favoritos.value = response.animaisfavoritos
        } catch (e: Exception) {
            Log.e("AnimalFavoritoVM", "Erro ao carregar favoritos", e)
        }
    }
}
