package com.example.adoteme_app.pets.presentation.ongs_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.api.OngApi
import com.example.adoteme_app.interfaces.OngApiService
import com.example.adoteme_app.model.OngResponseAllDto
import com.example.adoteme_app.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OngViewModel(
    private val ongApiService: OngApiService
) : ViewModel() {
    private val _ongs = MutableStateFlow<List<OngResponseAllDto>>(emptyList())
    val ongs: StateFlow<List<OngResponseAllDto>> = _ongs

    init {
        carregarOngs()
    }

    private fun carregarOngs() {
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
}
