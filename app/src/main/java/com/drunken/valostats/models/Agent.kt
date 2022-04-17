package com.drunken.valostats.models

data class Agent(
    val uuid: String,
    val displayName: String,
    val description: String,
    val developerName: String,
    val displayIcon: String,
    val displayIconSmall: String?,
    val fullPortrait: String?,
    val fullPortraitV2: String?,
    val role: AgentRole?,
    val abilities: List<Abilities>
)

data class AgentRole(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String
)

data class Abilities(
    val slot: String,
    val displayName: String,
    val description: String,
    val displayIcon: String?
)
