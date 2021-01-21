package br.com.luisccomp.backend.service.user

import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest
import br.com.luisccomp.backend.domain.model.request.user.UserUpdateRequest
import br.com.luisccomp.backend.domain.model.response.user.UserCreateResponse
import br.com.luisccomp.backend.domain.model.response.user.UserProfileResponse

interface UserService {
    fun createUser(userCreateRequest: UserCreateRequest): UserCreateResponse

    fun getUserProfile(): UserProfileResponse

    fun updateUserProfile(id: Long, userUpdateRequest: UserUpdateRequest): UserProfileResponse
}