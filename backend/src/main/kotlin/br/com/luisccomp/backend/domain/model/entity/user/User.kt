package br.com.luisccomp.backend.domain.model.entity.user

import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import java.time.ZoneOffset
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
@Table(name = "users")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, unique = true)
        var id: Long? = null,

        @Column(name = "first_name", nullable = false, length = 50)
        var firstName: String,

        @Column(name = "last_name", length = 50, nullable = false)
        var lastName: String,

        @Column(name = "email", nullable = false, unique = true)
        var email: String,

        @Column(name = "password", nullable = false)
        var password: String,

        @Column(name = "description", nullable = false)
        var description: String? = null,

        @Column(name = "active", nullable = false)
        var active: Boolean = true,

        @CreationTimestamp
        @Column(name = "created_at", nullable = false)
        var createdAt: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),

        @Column(name = "updated_at")
        var updatedAt: OffsetDateTime? = OffsetDateTime.now(ZoneOffset.UTC),

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var roles: List<UserRole> = mutableListOf()
)