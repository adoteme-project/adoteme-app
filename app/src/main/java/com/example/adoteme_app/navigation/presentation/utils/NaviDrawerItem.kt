package com.example.adoteme_app.navigation.presentation.utils

import android.graphics.drawable.Icon

sealed class NaviDrawerItem(
    val type: NavigationDrawerType,
    val badge: Int,
    val text: String,
) {

    data object AnimaisItem : NaviDrawerItem(
        type = NavigationDrawerType.ANIMAIS,
        badge = 0,
        text =  "Animais",
    )

    data object DoacoesItem : NaviDrawerItem(
        type = NavigationDrawerType.DOACOES,
        badge = 0,
        text =  "Doações",
    )

    data object OngsItem : NaviDrawerItem(
        type = NavigationDrawerType.ONGS,
        badge = 0,
        text =  "Ongs",
    )

    data object PerfilItem : NaviDrawerItem(
        type = NavigationDrawerType.PERFIL,
        badge = 0,
        text =  "Perfil",
    )

    enum class NavigationDrawerType {
        ANIMAIS,
        DOACOES,
        ONGS,
        PERFIL
    }

    companion object {
        val items = listOf(
            AnimaisItem,
            DoacoesItem,
            OngsItem,
            PerfilItem
        )
    }
}

