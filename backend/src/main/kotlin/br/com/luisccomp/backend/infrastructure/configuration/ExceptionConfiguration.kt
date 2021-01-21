package br.com.luisccomp.backend.infrastructure.configuration

import br.com.luisccomp.backend.exception.BaseHttpStatusException
import br.com.luisccomp.backend.exception.extension.toErrorResponse
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionConfiguration : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BaseHttpStatusException::class)
    fun handleBaseHttpStatus(exception: BaseHttpStatusException, request: WebRequest) = handleExceptionInternal(
            exception,
            exception.toErrorResponse(),
            HttpHeaders(),
            exception.status,
            request
    )
}