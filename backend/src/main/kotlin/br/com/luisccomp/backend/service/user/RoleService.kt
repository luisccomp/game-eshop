package br.com.luisccomp.backend.service.user

import br.com.luisccomp.backend.domain.model.entity.user.Role

interface RoleService {
    fun findRoleById(id: Long): Role
}