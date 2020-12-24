package br.com.luisccomp.backend.controller.api

import br.com.luisccomp.backend.domain.model.response.user.UserProfileResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/api/users")
interface UserController {

    @GetMapping("/profile")
    fun userProfile(): ResponseEntity<UserProfileResponse>

    @GetMapping("/{id}")
    fun userProfile(@PathVariable id: Long): ResponseEntity<UserProfileResponse>

}