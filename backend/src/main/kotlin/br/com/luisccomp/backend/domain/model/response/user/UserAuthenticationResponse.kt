package br.com.luisccomp.backend.domain.model.response.user

import com.fasterxml.jackson.annotation.JsonProperty

data class UserAuthenticationResponse(
        @JsonProperty("token")
        val token: String
)