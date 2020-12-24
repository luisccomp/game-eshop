package br.com.luisccomp.backend.controller.api.impl

import br.com.luisccomp.backend.controller.api.UserController
import br.com.luisccomp.backend.domain.model.response.user.UserProfileResponse
import br.com.luisccomp.backend.infrastructure.extension.getCurrentUser
import br.com.luisccomp.backend.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserControllerImpl(
        private val userService: UserService
) : UserController {

    override fun userProfile(): ResponseEntity<UserProfileResponse> {
        val currentUser = getCurrentUser()

        return ResponseEntity.ok(userService.findByEmail(currentUser.username))
    }

    override fun userProfile(id: Long): ResponseEntity<UserProfileResponse> {
        return ResponseEntity.ok(userService.findById(id))
    }

}