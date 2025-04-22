package com.example.adoteme_app.pets.presentation.ongs_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoteme_app.interfaces.OngApiService
import com.example.adoteme_app.model.Ong
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OngsViewModel(
    private val ongApiService: OngApiService
) : ViewModel() {
    private val _ongs = MutableStateFlow<List<Ong>>(emptyList())
    val ongs: StateFlow<List<Ong>> = _ongs

    init {
        Log.d("OngsViewModel", "View Model Ong criado")
    }

    fun listarOngs() {
        Log.d("OngsViewModel", "Listando todas as ongs")
        viewModelScope.launch {
            try {
                val response = ongApiService.getOngs()
                Log.d("OngsViewModel", "Ongs response: $response")
                _ongs.value = response
            } catch (e: Exception) {
                Log.e("OngsViewModel", "Erro ao carregar as ongs: $e")
            }
        }
    }

}