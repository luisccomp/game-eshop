package br.com.luisccomp.backend.service.user.impl

import br.com.luisccomp.backend.domain.model.entity.user.UserDetailsImpl
import br.com.luisccomp.backend.domain.model.entity.user.UserRole
import br.com.luisccomp.backend.domain.model.extension.entity.user.toUserCreateResponse
import br.com.luisccomp.backend.domain.model.extension.entity.user.toUserProfileResponse
import br.com.luisccomp.backend.domain.model.extension.request.user.toEntity
import br.com.luisccomp.backend.domain.model.request.user.UserCreateRequest
import br.com.luisccomp.backend.domain.model.request.user.UserUpdateRequest
import br.com.luisccomp.backend.domain.model.response.user.UserCreateResponse
import br.com.luisccomp.backend.domain.model.response.user.UserProfileResponse
import br.com.luisccomp.backend.enums.RoleEnum
import br.com.luisccomp.backend.exception.BadRequestException
import br.com.luisccomp.backend.exception.NotFoundException
import br.com.luisccomp.backend.exception.UnauthorizedException
import br.com.luisccomp.backend.infrastructure.getCurrentUser
import br.com.luisccomp.backend.repository.user.UserRepository
import br.com.luisccomp.backend.service.user.RoleService
import br.com.luisccomp.backend.service.user.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        val userRepository: UserRepository,
        val passwordEncoder: PasswordEncoder,
        val roleService: RoleService
) : UserService {
    override fun createUser(userCreateRequest: UserCreateRequest): UserCreateResponse {
        if (userRepository.existsByEmail(userCreateRequest.email)) {
            throw BadRequestException("User email already in use")
        }

        val user = userCreateRequest.toEntity()
        user.password = passwordEncoder.encode(user.password)

        val role = roleService.findRoleById(RoleEnum.USER.value)

        val userRole = UserRole(
                user = user,
                role = role
        )

        user.roles = mutableListOf(userRole)

        return userRepository.save(user)
                .toUserCreateResponse()
    }

    override fun getUserProfile(): UserProfileResponse {
        val user = userRepository.findByEmail(getCurrentUser().username)
                .orElseThrow { NotFoundException("User not found") }

        return if (user.id == (getCurrentUser() as UserDetailsImpl).user.id) {
            user.toUserProfileResponse()
        } else {
            throw UnauthorizedException("Access denied")
        }
    }

    override fun updateUserProfile(id: Long, userUpdateRequest: UserUpdateRequest): UserProfileResponse {
        val user = userRepository.findById(id)
                .orElseThrow { NotFoundException("User not found") }

        if (user.email != getCurrentUser().username) {
            throw UnauthorizedException("Access denied")
        }

        user.firstName = userUpdateRequest.firstName
        user.lastName = userUpdateRequest.lastName
        user.description = userUpdateRequest.description

        return userRepository.save(user)
                .toUserProfileResponse()
    }
}