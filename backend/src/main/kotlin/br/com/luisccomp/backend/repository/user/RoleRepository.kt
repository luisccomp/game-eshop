package br.com.luisccomp.backend.repository.user

import br.com.luisccomp.backend.domain.model.entity.user.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {

}