package br.com.luisccomp.backend.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

abstract class BaseHttpStatusException(
        message: String?,
        val status: HttpStatus,
        val details: Array<Any>?
) : RuntimeException(message)