package br.com.luisccomp.backend.domain.model.response.error

import com.fasterxml.jackson.annotation.JsonProperty

class ErrorResponse(
        @JsonProperty("status")
        val status: Int,

        @JsonProperty("message")
        val message: String? = null,

        @JsonProperty("details")
        val details: Array<Any>? = null
)