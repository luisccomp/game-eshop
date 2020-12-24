package br.com.luisccomp.backend.domain.model.entity.user

import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "user")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        var id: Long? = null,

        @Column(name = "first_name", length = 50, nullable = false)
        var firstName: String,

        @Column(name = "last_name", length = 50, nullable = false)
        var lastName: String,

        @Column(name = "email", length = 250, nullable = false, unique = true)
        var email: String,

        @Column(name = "password", length = 250, nullable = false)
        var password: String,

        @Column(name = "description", length = 250)
        var description: String? = null,

        @Column(name = "active", nullable = false)
        var active: Boolean = true,

        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "user_id")
        var roles: Set<UserRole> = setOf(),

        @CreationTimestamp
        @Column(name = "created_at", nullable = false)
        var createdAt: OffsetDateTime = OffsetDateTime.now(),

        @Column(name = "updated_at", nullable = false)
        var updatedAt: OffsetDateTime? = null
)