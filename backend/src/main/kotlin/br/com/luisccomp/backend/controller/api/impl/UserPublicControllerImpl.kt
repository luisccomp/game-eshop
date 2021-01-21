package br.com.luisccomp.backend.controller.api.impl

import br.com.luisccomp.backend.controller.api.UserPublicController
import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest
import br.com.luisccomp.backend.domain.model.response.user.UserCreateResponse
import br.com.luisccomp.backend.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class UserPublicControllerImpl(
        val userService: UserService
) : UserPublicController {
    override fun createUser(createRequest: UserCreateRequest): ResponseEntity<UserCreateResponse> {
        val userCreateResponse = userService.createUser(createRequest)

        val uri = URI.create("/users/${userCreateResponse.id}")

        return ResponseEntity.created(uri)
                .body(userCreateResponse)
    }
}