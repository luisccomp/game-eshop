package br.com.luisccomp.backend.domain.model.request.user

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UserCreateRequest(
        @JsonProperty("first_name")
        @field:NotBlank(message = "first_name should not contains an empty string")
        val firstName: String,

        @JsonProperty("last_name")
        @field:NotBlank(message = "last_name name should not contains an empty string")
        val lastName: String,

        @JsonProperty("email")
        @field:Email(message = "email should contains a valid email address")
        val email: String,

        @JsonProperty("password")
        @field:NotBlank(message = "password name should not contains an empty string")
        @field:Length(min = 8, max = 20, message = "Password must have length between 8 and 20 characters")
        val password: String,

        @JsonProperty("description")
        val description: String? = null
)