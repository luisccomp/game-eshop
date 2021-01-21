package br.com.luisccomp.backend.controller.api

import br.com.luisccomp.backend.domain.model.request.user.UserAuthenticationRequest
import br.com.luisccomp.backend.domain.model.response.user.UserAuthenticationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@RequestMapping("/auth")
interface AuthenticationController {
    @PostMapping("/login")
    fun login(@RequestBody @Valid userAuthenticationRequest: UserAuthenticationRequest): ResponseEntity<UserAuthenticationResponse>
}