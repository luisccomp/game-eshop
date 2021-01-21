package br.com.luisccomp.backend.domain.model.response.error

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorResponse(
        @JsonProperty("status")
        val status: Int,

        @JsonProperty("message")
        val message: String? = null,

        @JsonProperty("details")
        val details: Any? = null
) {
    internal data class ErrorResponseDetail(
            @JsonProperty("field")
            val field: String,

            @JsonProperty("error")
            val error: String
    )
}
