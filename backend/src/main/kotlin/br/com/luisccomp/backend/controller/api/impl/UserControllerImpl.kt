package br.com.luisccomp.backend.controller.api.impl

import br.com.luisccomp.backend.controller.api.UserController
import br.com.luisccomp.backend.domain.model.request.user.UserUpdateRequest
import br.com.luisccomp.backend.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserControllerImpl(
        private val userService: UserService
) : UserController {
    override fun getUserProfile() = ResponseEntity.ok(userService.getUserProfile())

    override fun updateUserProfile(id: Long, userUpdateRequest: UserUpdateRequest) = ResponseEntity.ok(
            userService.updateUserProfile(id, userUpdateRequest)
    )
}