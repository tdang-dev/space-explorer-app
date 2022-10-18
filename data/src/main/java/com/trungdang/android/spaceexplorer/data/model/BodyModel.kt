package com.trungdang.android.spaceexplorer.data.model

data class BodyModel(
    val id: String = "",
    val name: String = "",
    val englishName: String = "",
    val isPlanet: Boolean = false,
    val moon: MutableList<MoonLinkModel> = mutableListOf(),
)
