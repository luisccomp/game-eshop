package br.com.luisccomp.backend.controller.api.impl

import br.com.luisccomp.backend.controller.api.UserPublicController
import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest
import br.com.luisccomp.backend.domain.model.response.user.UserCreateResponse
import br.com.luisccomp.backend.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserPublicControllerImpl(
        private val userService: UserService,
) : UserPublicController {

    override fun create(userCreateRequest: UserCreateRequest): ResponseEntity<UserCreateResponse> =
            ResponseEntity(userService.create(userCreateRequest), HttpStatus.CREATED)
    
}