package br.com.luisccomp.backend.infrastructure

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

fun Any.getCurrentUser() = SecurityContextHolder.getContext()
        .authentication
        .principal as UserDetails