package br.com.luisccomp.backend.controller.api

import br.com.luisccomp.backend.domain.model.request.user.UserUpdateRequest
import br.com.luisccomp.backend.domain.model.response.user.UserProfileResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@RequestMapping("/api/users")
interface UserController {
    @GetMapping("/profile")
    fun getUserProfile(): ResponseEntity<UserProfileResponse>

    @PatchMapping("/{id}")
    fun updateUserProfile(@PathVariable id: Long, @RequestBody @Valid userUpdateRequest: UserUpdateRequest): ResponseEntity<UserProfileResponse>
}