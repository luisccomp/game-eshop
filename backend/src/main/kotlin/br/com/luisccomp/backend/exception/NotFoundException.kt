package br.com.luisccomp.backend.exception

import org.springframework.http.HttpStatus

class NotFoundException(
        message: String? = null,
        details: Array<Any>? = null
) : BaseHttpStatusException(message, HttpStatus.NOT_FOUND, details)