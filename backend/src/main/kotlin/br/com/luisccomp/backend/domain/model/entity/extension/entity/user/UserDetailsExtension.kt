package br.com.luisccomp.backend.domain.model.entity.extension.entity.user

import br.com.luisccomp.backend.domain.model.entity.user.UserDetailsImpl
import br.com.luisccomp.backend.domain.model.entity.user.UserJwtPayload
import org.springframework.security.core.userdetails.UserDetails

fun UserDetails.toUserJwtPayload() = (this as UserDetailsImpl).let {
    UserJwtPayload(
            id = it.user.id,
            email = it.user.email,
            roles = it.user.roles.map { role -> role.toString() }.toSet()
    )
}
