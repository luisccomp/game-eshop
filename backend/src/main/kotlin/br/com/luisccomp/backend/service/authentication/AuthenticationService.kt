package br.com.luisccomp.backend.service.authentication

import br.com.luisccomp.backend.domain.model.request.user.UserAuthenticationRequest
import br.com.luisccomp.backend.domain.model.response.user.UserAuthenticationResponse

interface AuthenticationService {
    fun login(userAuthenticationRequest: UserAuthenticationRequest): UserAuthenticationResponse
}