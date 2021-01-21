package br.com.luisccomp.backend.domain.model.request.user

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class UserUpdateRequest(
        @JsonProperty("first_name")
        @field:NotBlank(message = "Field \"first_name\" doesn't allow empty strings")
        @field:Length(max = 50, message = "Field \"first_name\" must contain up to 50 characters")
        val firstName: String,

        @JsonProperty("last_name")
        @field:NotBlank(message = "Field \"last_name\" doesn't allow empty strings")
        @field:Length(max = 50, message = "Field \"last_name\" must contain up to 50 characters")
        val lastName: String,

        @JsonProperty("description")
        val description: String? = null
)