package br.com.luisccomp.backend.domain.model.entity.user

import br.com.luisccomp.backend.enums.RoleEnum
import org.springframework.security.core.GrantedAuthority
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "roles")
class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, unique = true)
        var id: Long? = null,

        @Column(name = "role", nullable = false)
        @Enumerated(EnumType.STRING)
        var role: RoleEnum,

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "role_id")
        var users: List<UserRole> = mutableListOf()
) : GrantedAuthority {
    override fun getAuthority() = role.toString()
}