package br.com.luisccomp.backend.service.user.impl

import br.com.luisccomp.backend.exception.NotFoundException
import br.com.luisccomp.backend.repository.user.RoleRepository
import br.com.luisccomp.backend.service.user.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
        private val roleRepository: RoleRepository
) : RoleService {
    override fun findRoleById(id: Long) = roleRepository.findById(id)
            .orElseThrow { NotFoundException("Role not found") }
}