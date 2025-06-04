package com.example.adoteme_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.data.network.api.AnimalApiService
import com.example.adoteme_app.domain.model.AnimalUiFavoritoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response


class AnimalFavoritoViewModel(
    private val animalService: AnimalApiService,
) : ViewModel() {


    private val _favoritosCompletos = MutableStateFlow<List<AnimalUiFavoritoDto>>(emptyList())
    val favoritosCompletos: StateFlow<List<AnimalUiFavoritoDto>> = _favoritosCompletos.asStateFlow()


    private val _favoritosIds = MutableStateFlow<Set<Long>>(emptySet())
    val favoritosIds: StateFlow<Set<Long>> = _favoritosIds.asStateFlow()

    suspend fun carregarFavoritos(idAdotante: Long) {
        try {
            val response = animalService.getFavoritosByAdotanteId(idAdotante)
            _favoritosCompletos.value = response.animaisfavoritos
            _favoritosIds.value = response.animaisfavoritos.map { it.animalId }.toSet()
        } catch (e: Exception) {
            Log.e("AnimalFavoritoVM", "Erro ao carregar favoritos", e)
        }
    }

    fun favoritarOuDesfavoritar(idAdotante: Long, idAnimal: Long) {
        viewModelScope.launch {
            try {
                val jaFavoritado = _favoritosIds.value.contains(idAnimal)

                val response: Response<Unit> = if (jaFavoritado) {
                    animalService.desfavoritarAnimal(idAdotante, idAnimal)
                } else {
                    animalService.favoritarAnimal(idAdotante, idAnimal)
                }

                if (response.isSuccessful) {
                    _favoritosIds.update { currentIds ->
                        if (jaFavoritado) currentIds - idAnimal else currentIds + idAnimal
                    }

                    _favoritosCompletos.update { lista ->
                        if (jaFavoritado) {
                            lista.filterNot { it.animalId == idAnimal }
                        } else {
                            lista + AnimalUiFavoritoDto(
                                animalId = idAnimal,
                                nome = "",
                                idade = 0,
                                especie = "",
                                sexo = "",
                                porte = "",
                                distancia = 0,
                                imagem = "",
                                isFavoritado = true
                            )
                        }
                    }
                } else {
                    Log.e("Favoritos", "Erro na operação: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Favoritos", "Falha ao (des)favoritar", e)
            }
        }
    }
}

