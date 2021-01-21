package br.com.luisccomp.backend.controller.api.impl

import br.com.luisccomp.backend.controller.api.AuthenticationController
import br.com.luisccomp.backend.domain.model.request.user.UserAuthenticationRequest
import br.com.luisccomp.backend.service.authentication.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationControllerImpl(
    private val authenticationService: AuthenticationService
) : AuthenticationController {
    override fun login(userAuthenticationRequest: UserAuthenticationRequest) =
            ResponseEntity.ok(authenticationService.login(userAuthenticationRequest))
}