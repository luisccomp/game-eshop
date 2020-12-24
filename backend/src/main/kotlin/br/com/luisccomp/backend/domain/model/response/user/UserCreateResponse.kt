package br.com.luisccomp.backend.domain.model.response.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UserCreateResponse(
        @JsonProperty("id")
        val id: Long?,

        @JsonProperty("email")
        val email: String
)