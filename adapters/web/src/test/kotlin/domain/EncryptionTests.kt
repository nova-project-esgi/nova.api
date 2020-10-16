package domain

import com.esgi.nova.adapters.web.authentication.Encryption
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class EncryptionTests {

    @Test
    fun shouldEncryptPassword() {
        val res = Encryption.md5("TOTO")
        assertNotNull(res)
    }
}
