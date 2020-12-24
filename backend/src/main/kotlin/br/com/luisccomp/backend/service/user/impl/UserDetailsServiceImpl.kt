package br.com.luisccomp.backend.service.user.impl

import br.com.luisccomp.backend.domain.model.entity.extension.entity.user.toUserDetails
import br.com.luisccomp.backend.repository.user.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
        private val userRepository: UserRepository
) : UserDetailsService {

    /**
     * In this project, username is the same as email.
     */
    override fun loadUserByUsername(username: String) =
            userRepository.findByEmail(username)
                    .orElseThrow { UsernameNotFoundException("User not found.") }
                    .toUserDetails()

}