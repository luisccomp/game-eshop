package br.com.luisccomp.backend.domain.model.entity.extension.request.user

import br.com.luisccomp.backend.domain.model.entity.user.User
import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest

fun UserCreateRequest.toEntity() = User(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password,
        description = this.description
)