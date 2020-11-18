package com.esgi.nova.adapters.web.authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm


open class JWTAuthentication(secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier = JWT.require(algorithm).build()
    fun decode(token: String): String? =
            JWT.decode(token).claims["name"]?.asString()
    fun sign(name: String): String = JWT.create().withClaim("name", name).sign(algorithm)
}