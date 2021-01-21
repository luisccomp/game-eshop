package br.com.luisccomp.backend.controller.api

import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest
import br.com.luisccomp.backend.domain.model.response.user.UserCreateResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@RequestMapping("/public/api/users")
interface UserPublicController {
    @PostMapping
    fun createUser(@RequestBody @Valid createRequest: UserCreateRequest): ResponseEntity<UserCreateResponse>
}