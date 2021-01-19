package br.com.luisccomp.backend.controller.api.impl

import br.com.luisccomp.backend.controller.api.AuthenticationController
import br.com.luisccomp.backend.domain.model.entity.user.UserDetailsImpl
import br.com.luisccomp.backend.domain.model.request.user.UserAuthenticationRequest
import br.com.luisccomp.backend.domain.model.response.user.UserAuthenticationResponse
import br.com.luisccomp.backend.exception.UnauthorizedException
import br.com.luisccomp.backend.infrastructure.components.JwtTokenUtil
import br.com.luisccomp.backend.service.authentication.AuthenticationService
import br.com.luisccomp.backend.service.user.impl.UserDetailsServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationControllerImpl(
        private val authenticationService: AuthenticationService
) : AuthenticationController {

    override fun login(userAuthenticationRequest: UserAuthenticationRequest): ResponseEntity<UserAuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.login(userAuthenticationRequest))
    }

}