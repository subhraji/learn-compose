package com.example.jetpackcomposecrashcourse.proto_datastore

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val language: Language = Language.BENGALI,
    val knownLocations: PersistentList<Location> = persistentListOf()
)
@Serializable
data class Location(
    val lat: Double,
    val lng: Double
)
enum class Language{
    ENGLISH, GERMAN, BENGALI
}