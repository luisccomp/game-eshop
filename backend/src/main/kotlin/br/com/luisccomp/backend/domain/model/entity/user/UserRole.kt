package br.com.luisccomp.backend.domain.model.entity.user

import br.com.luisccomp.backend.enums.RoleEnum
import org.springframework.security.core.GrantedAuthority
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "user_role")
class UserRole(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        val id: Long? = null,

        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        val role: RoleEnum,

        @ManyToOne
        val user: User
) : GrantedAuthority {
        override fun getAuthority() = role.toString()
}