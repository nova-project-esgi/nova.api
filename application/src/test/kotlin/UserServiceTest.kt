import com.esgi.nova.application.services.users.UsersService
import com.esgi.nova.application.services.users.models.ConnectedUser
import com.esgi.nova.common.security.JWTAuthentication
import com.esgi.nova.core_api.user.Role
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByTokenException
import com.esgi.nova.core_api.user.exceptions.UserNotFoundByUsernameException
import com.esgi.nova.core_api.user.queries.FindUserByUsernameQuery
import com.esgi.nova.core_api.user.views.UserCredential
import com.esgi.nova.core_api.user.views.UserResume
import io.mockk.every
import io.mockk.mockk
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.extensions.kotlin.query
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq

import org.springframework.util.Assert
import java.util.*
import java.util.concurrent.CompletableFuture

fun <T> getCompletableFuture(l: T): CompletableFuture<T> {
    return mockk { every { join() } returns (l) }
}


class UserServiceTest {

    val cmdGatewayMock = mockk<CommandGateway>()
    val queryGatewayMock = mockk<QueryGateway>()
    val sut = UsersService(cmdGatewayMock, queryGatewayMock)

    val existingUserCredential = UserCredential("thomasLeTrain", "iliketrains")
    val existingUserUUID = UUID.randomUUID()
    val existingUserResume = UserResume(existingUserUUID, "toto@mail.fr", Role.USER, "thomasLeTrain")
    val existingConnectedUser = ConnectedUser(
            email = "toto@mail.fr",
            role = Role.USER,
            username = "thomasLeTrain",
            token = JWTAuthentication.sign("thomasLeTrain"),
            id = existingUserUUID
    )

    @BeforeEach
    fun init() {
        every { queryGatewayMock.query(FindUserByUsernameQuery("thomasLeTrain"), UserCredential::class.java) }
                .returns(getCompletableFuture(existingUserCredential))
        every { queryGatewayMock.query(FindUserByUsernameQuery("totoLerigolo"), UserCredential::class.java) }
                .returns(getCompletableFuture(null))


        every { queryGatewayMock.query(FindUserByUsernameQuery("thomasLeTrain"), UserResume::class.java) }
                .returns(getCompletableFuture(existingUserResume))
        every { queryGatewayMock.query(FindUserByUsernameQuery("totoLerigolo"), UserResume::class.java) }
                .returns(getCompletableFuture(null))

    }


    @Test
    fun should_get_credentials_of_existing_user_by_name() {
        val userFound = sut.getCredentialByUsername("thomasLeTrain");
        val userExpected = existingUserCredential
        assert(userFound == userExpected)
    }

    @Test
    fun should_not_get_credentials_with_invalid_name() {
        Assertions.assertThrows(UserNotFoundByUsernameException::class.java) {
            sut.getCredentialByUsername("totoLerigolo")
        }
    }

    @Test
    fun should_getResumeByUsername() {
        val userFound = sut.getResumeByUsername("thomasLeTrain");
        val userExpected = existingUserResume
        assert(userFound == userExpected)
    }

    @Test
    fun should_not_get_resume_when_invalid_name() {
        Assertions.assertThrows(UserNotFoundByUsernameException::class.java) {
            sut.getResumeByUsername("totoLerigolo")
        }
    }

    @Test
    fun should_getByToken() {
        val userFound = sut.getByToken(existingConnectedUser.token)
        val userExpected = existingConnectedUser
        assert(userFound.email == userExpected.email)
        assert(userFound.username == userExpected.username)
        assert(userFound.id == userExpected.id)
        assert(userFound.role == userExpected.role)
        assert(userFound.email == userExpected.email)
    }

    @Test
    fun should_not_get_user_with_invalid_token() {
        Assertions.assertThrows(UserNotFoundByTokenException::class.java) {
            sut.getByToken(JWTAuthentication.sign("totoLerigolo"))
        }
    }
}