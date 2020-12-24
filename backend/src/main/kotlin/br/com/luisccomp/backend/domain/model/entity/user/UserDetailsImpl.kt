package br.com.luisccomp.backend.domain.model.entity.user

import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val user: User): UserDetails {
    override fun getAuthorities() = user.roles

    override fun getPassword() = user.password

    override fun getUsername() = user.email

    override fun isAccountNonExpired() = user.active

    override fun isAccountNonLocked() = user.active

    override fun isCredentialsNonExpired() = user.active

    override fun isEnabled() = user.active
}