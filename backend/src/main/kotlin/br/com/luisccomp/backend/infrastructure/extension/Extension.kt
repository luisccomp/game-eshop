package br.com.luisccomp.backend.infrastructure.extension

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

fun getCurrentUser() = SecurityContextHolder.getContext()
        .authentication
        .principal as UserDetails