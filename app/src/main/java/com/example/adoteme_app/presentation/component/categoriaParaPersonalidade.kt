package com.example.adoteme_app.presentation.component

import com.example.adoteme_app.domain.model.PersonalidadeDto

val categoriaParaPersonalidade: Map<String, (PersonalidadeDto) -> Int> = mapOf(
    "Brincalhões" to { it.energia },
    "Curiosos" to { it.obediente },
    "Comportados" to { it.energia },
    "Donos do Pedaço" to { it.territorial },
    "Calmos" to { it.tolerante },
    "Amigáveis" to { it.inteligencia }
)
