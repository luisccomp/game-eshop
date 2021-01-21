package br.com.luisccomp.backend.domain.model.request.user

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class UserAuthenticationRequest(
        @JsonProperty("email")
        @field:NotBlank(message = "Field \"email\" doesn't allow empty strings")
        @field:Email(message = "Field \"email\" must contain a valid email address")
        @field:Length(max = 255, message = "Field \"email\" must contain up to 255 characters")
        val email: String,

        @JsonProperty("password")
        @field:NotBlank(message = "Field \"password\" doesn't allow empty strings")
        @field:Length(max = 255, message = "Field \"password\" must contain up to 255 characters")
        val password: String
)