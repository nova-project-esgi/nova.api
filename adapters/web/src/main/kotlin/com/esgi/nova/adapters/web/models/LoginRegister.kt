package com.esgi.nova.adapters.web.models

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class LoginRegister(var username: String, var password: String)