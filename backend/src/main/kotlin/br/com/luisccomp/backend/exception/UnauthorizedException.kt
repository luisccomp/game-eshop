package br.com.luisccomp.backend.exception

import org.springframework.http.HttpStatus

class UnauthorizedException(
        message: String? = null,
        details: Array<Any>? = null
) : BaseHttpStatusException(message, HttpStatus.UNAUTHORIZED, details)