package br.com.luisccomp.backend.exception

import org.springframework.http.HttpStatus

class BadRequestException(
        message: String? = null,
        details: Array<Any>? = null
) : BaseHttpStatusException(message, HttpStatus.BAD_REQUEST, details)