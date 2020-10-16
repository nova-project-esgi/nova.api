package authentication

import com.esgi.nova.adapters.web.authentication.JWTAuthentication
import org.junit.jupiter.api.Test

class JWTAuthenticationTest {

    private val secret = "test"
    private val content = "content"

    @Test
    fun shouldSignContent() {
        val jwt = JWTAuthentication(secret)
        jwt.sign(content)
        println(content)
    }
}