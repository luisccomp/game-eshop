package br.com.luisccomp.backend.service.user

import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest
import br.com.luisccomp.backend.domain.model.response.user.UserCreateResponse
import br.com.luisccomp.backend.domain.model.response.user.UserProfileResponse

interface UserService {

    fun create(userCreateRequest: UserCreateRequest): UserCreateResponse

    fun findById(id: Long): UserProfileResponse

    fun findByEmail(email: String): UserProfileResponse

}