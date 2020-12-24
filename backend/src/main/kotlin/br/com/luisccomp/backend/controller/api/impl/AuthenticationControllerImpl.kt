package br.com.luisccomp.backend.controller.api.impl

import br.com.luisccomp.backend.controller.api.AuthenticationController
import br.com.luisccomp.backend.domain.model.entity.user.UserDetailsImpl
import br.com.luisccomp.backend.domain.model.request.user.UserAuthenticationRequest
import br.com.luisccomp.backend.domain.model.response.user.UserAuthenticationResponse
import br.com.luisccomp.backend.exception.UnauthorizedException
import br.com.luisccomp.backend.infrastructure.components.JwtTokenUtil
import br.com.luisccomp.backend.service.user.impl.UserDetailsServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationControllerImpl(
        private val userDetailsService: UserDetailsServiceImpl,
        private val jwtTokenUtil: JwtTokenUtil,
        private val authenticationManager: AuthenticationManager
) : AuthenticationController {

    override fun login(userAuthenticationRequest: UserAuthenticationRequest): ResponseEntity<UserAuthenticationResponse> {
        authenticate(userAuthenticationRequest.email, userAuthenticationRequest.password)

        val userDetails = userDetailsService.loadUserByUsername(userAuthenticationRequest.email)

        val claims = (userDetails as UserDetailsImpl).let {
            mutableMapOf(
                    Pair("id", it.user.id as Any),
                    Pair("roles", it.user.roles as Any)
            )
        }

        val userAuthenticationResponse = UserAuthenticationResponse(
                token = jwtTokenUtil.generateToken(userDetails, claims)
        )

        return ResponseEntity.ok(userAuthenticationResponse)
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