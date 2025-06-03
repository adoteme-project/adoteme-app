package com.example.adoteme_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.data.network.api.OngApiService
import com.example.adoteme_app.domain.model.OngDadosBancariosAnimalDto
import com.example.adoteme_app.domain.model.OngResponseAllDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OngViewModel(
    private val ongApiService: OngApiService
) : ViewModel() {
    private val _ongs = MutableStateFlow<List<OngResponseAllDto>>(emptyList())
    val ongs: StateFlow<List<OngResponseAllDto>> = _ongs

    private val _ongSelecionada = MutableStateFlow<OngDadosBancariosAnimalDto?>(null)
    val ongSelecionada: StateFlow<OngDadosBancariosAnimalDto?> = _ongSelecionada

    init {
        carregarOngs()
    }

     fun carregarOngs() {
        viewModelScope.launch {
            try {
                val response = ongApiService.getOngs()
                if (response.isSuccessful) {
                    response.body()?.let { _ongs.value = it }
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Erro ao carregar animais", e)
            }
        }
    }

    fun carregarOngPorId(ongId: Long) {
        viewModelScope.launch {
            try {
                val response = ongApiService.getOngsComAnimais()
                if (response.isSuccessful) {
                    val lista = response.body()
                    val ongFiltrada = lista?.find { it.id == ongId }
                    _ongSelecionada.value = ongFiltrada
                }
            } catch (e: Exception) {
                Log.e("OngViewModel", "Erro ao carregar ONG por ID", e)
            }
        }
    }
}
