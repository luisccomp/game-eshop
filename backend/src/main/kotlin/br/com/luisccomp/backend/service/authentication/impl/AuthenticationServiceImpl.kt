package br.com.luisccomp.backend.service.authentication.impl

import br.com.luisccomp.backend.domain.model.entity.user.UserDetailsImpl
import br.com.luisccomp.backend.domain.model.request.user.UserAuthenticationRequest
import br.com.luisccomp.backend.domain.model.response.user.UserAuthenticationResponse
import br.com.luisccomp.backend.exception.UnauthorizedException
import br.com.luisccomp.backend.infrastructure.components.JwtTokenUtil
import br.com.luisccomp.backend.service.authentication.AuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
        private val userDetailsService: UserDetailsService,
        private val jwtTokenUtil: JwtTokenUtil,
        private val authenticationManager: AuthenticationManager
) : AuthenticationService {

    override fun login(userAuthenticationRequest: UserAuthenticationRequest): UserAuthenticationResponse {
        authenticate(userAuthenticationRequest.email, userAuthenticationRequest.password)

        val userDetails = userDetailsService.loadUserByUsername(userAuthenticationRequest.email)

        val claims = (userDetails as UserDetailsImpl).let {
            mutableMapOf(
                    Pair("id", it.user.id as Any),
                    Pair("roles", it.user.roles as Any)
            )
        }

        return UserAuthenticationResponse(
                token = jwtTokenUtil.generateToken(userDetails, claims)
        )
    }

    private fun authenticate(email: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(email, password))
        } catch (e: DisabledException) {
            throw UnauthorizedException("User disabled")
        } catch (e: BadCredentialsException) {
            throw UnauthorizedException("Invalid username or password")
        }
    }

}