package com.morozov.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationObject

@Serializable
data object Splash : NavigationObject()

@Serializable
data object Books : NavigationObject()

@Serializable
data class BookDetail(
    val author: String = "",
    val description: String = "",
    val id: Int = 0,
    val image: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val titlee: String = ""
) : NavigationObject()

@Serializable
data object AboutMe : NavigationObject()


