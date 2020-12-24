package br.com.luisccomp.backend.service.user.impl

import br.com.luisccomp.backend.domain.model.entity.extension.entity.user.toUserCreateResponse
import br.com.luisccomp.backend.domain.model.entity.extension.entity.user.toUserProfileResponse
import br.com.luisccomp.backend.domain.model.entity.extension.request.user.toEntity
import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest
import br.com.luisccomp.backend.domain.model.response.user.UserCreateResponse
import br.com.luisccomp.backend.domain.model.response.user.UserProfileResponse
import br.com.luisccomp.backend.exception.BadRequestException
import br.com.luisccomp.backend.exception.NotFoundException
import br.com.luisccomp.backend.exception.UnauthorizedException
import br.com.luisccomp.backend.infrastructure.extension.getCurrentUser
import br.com.luisccomp.backend.repository.user.UserRepository
import br.com.luisccomp.backend.service.user.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder
): UserService {

    override fun create(userCreateRequest: UserCreateRequest): UserCreateResponse {
        if (!userRepository.existsByEmail(userCreateRequest.email)) {
            val user = userCreateRequest.toEntity()

            user.password = passwordEncoder.encode(user.password)

            return userRepository.save(user)
                    .toUserCreateResponse()
        } else {
            throw BadRequestException("User email already registered")
        }
    }

    override fun findById(id: Long): UserProfileResponse {
        val user = userRepository.findById(id)
                .orElseThrow { NotFoundException("User not found") }

        if (getCurrentUser().username != user.email) {
            throw UnauthorizedException("Access denied")
        }

        return user.toUserProfileResponse()
    }

    override fun findByEmail(email: String): UserProfileResponse {
        return userRepository.findByEmail(email)
                .orElseThrow { NotFoundException("User not found") }
                .toUserProfileResponse()
    }

}