package br.com.luisccomp.backend.exception.extension

import br.com.luisccomp.backend.domain.model.response.error.ErrorResponse
import br.com.luisccomp.backend.exception.BaseHttpStatusException

fun BaseHttpStatusException.toErrorResponse() = ErrorResponse(
        status = this.status.value(),
        message = this.message,
        details = this.details
)