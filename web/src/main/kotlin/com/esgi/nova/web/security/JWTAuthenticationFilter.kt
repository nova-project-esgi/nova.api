package com.esgi.nova.web.security

import com.esgi.nova.common.security.JWTAuthentication
import com.esgi.nova.core_api.user.views.UserCredential
import com.esgi.nova.common.security.SecurityConstants.HEADER_STRING
import com.esgi.nova.common.security.SecurityConstants.TOKEN_PREFIX
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter(authenticationManager) {
    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(req: HttpServletRequest,
                                       res: HttpServletResponse): Authentication {
        return try {
            val creds: UserCredential = ObjectMapper()
                    .readValue(req.inputStream, UserCredential::class.java)
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            creds.username,
                            creds.password,
                            ArrayList())
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest?,
                                          res: HttpServletResponse,
                                          chain: FilterChain?,
                                          auth: Authentication) {

        val token: String = JWTAuthentication.sign((auth.principal as User).username)
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
    }
}