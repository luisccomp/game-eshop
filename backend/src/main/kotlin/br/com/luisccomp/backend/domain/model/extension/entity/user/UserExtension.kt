package br.com.luisccomp.backend.domain.model.extension.entity.user

import br.com.luisccomp.backend.domain.model.entity.user.User
import br.com.luisccomp.backend.domain.model.entity.user.UserDetailsImpl
import br.com.luisccomp.backend.domain.model.response.user.UserCreateResponse
import br.com.luisccomp.backend.domain.model.response.user.UserProfileResponse

fun User.toUserDetails() = UserDetailsImpl(this)

fun User.toUserCreateResponse() = UserCreateResponse(
        id = this.id,
        email = this.email
)

fun User.toUserProfileResponse() = UserProfileResponse(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        description = this.description
)