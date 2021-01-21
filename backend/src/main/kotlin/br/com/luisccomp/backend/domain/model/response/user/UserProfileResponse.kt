package br.com.luisccomp.backend.domain.model.response.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UserProfileResponse(
        @JsonProperty("id")
        val id: Long? = null,

        @JsonProperty("first_name")
        val firstName: String,

        @JsonProperty("last_name")
        val lastName: String,

        @JsonProperty("email")
        val email: String,

        @JsonProperty("description")
        val description: String? = null
)
