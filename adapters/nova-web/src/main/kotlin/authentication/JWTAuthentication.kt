package authentication

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm


open class JWTAuthentication(val secret: String) {
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier = JWT.require(algorithm).build()
    fun sign(name: String): String = JWT.create().withClaim("name", name).sign(algorithm)
}