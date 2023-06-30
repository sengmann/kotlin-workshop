package com.w11k.dto

import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val id: Int?,
    val name: String,
    val description: String,
    val radius: Double,
    val gravitation: Double,
    val positionInSystem: Int,
);
