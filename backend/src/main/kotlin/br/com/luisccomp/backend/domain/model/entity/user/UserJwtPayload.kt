package br.com.luisccomp.backend.domain.model.entity.user

class UserJwtPayload(
        val id: Long?,
        val email: String,
        val roles: Set<String> = setOf()
)