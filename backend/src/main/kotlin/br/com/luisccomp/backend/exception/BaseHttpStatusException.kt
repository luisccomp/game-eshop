package br.com.luisccomp.backend.exception

import org.springframework.http.HttpStatus

abstract class BaseHttpStatusException(
        message: String?,
        val status: HttpStatus,
        val details: Any?
) : RuntimeException(message)