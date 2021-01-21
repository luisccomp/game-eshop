package br.com.luisccomp.backend.domain.model.extension.request.user

import br.com.luisccomp.backend.domain.model.entity.user.User
import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest

fun UserCreateRequest.toEntity(id: Long? = null) = User(
        id = id,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        password = this.password,
        description = this.description
)