package com.esgi.nova.ports.provided.dtos

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class UserDto(var username: String, var password: String)