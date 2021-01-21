package br.com.luisccomp.backend.infrastructure.filter

import br.com.luisccomp.backend.infrastructure.component.JwtTokenUtil
import br.com.luisccomp.backend.service.user.impl.UserDetailsServiceImpl
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
        private val userDetailsService: UserDetailsServiceImpl,
        private val jwtTokenUtil: JwtTokenUtil
) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorization = request.getHeader("Authorization")

        try {
            // The JWT token is in the form "Bearer ...
            if (authorization != null && authorization.startsWith("Bearer ")) {
                val token = authorization.substring(7)

                val username = jwtTokenUtil.getUserNameFromToken(token)

                // Once we get the token validate it
                if (username != null && SecurityContextHolder.getContext().authentication == null) {
                    val userDetails = userDetailsService.loadUserByUsername(username)

                    // if token is valid configure Spring Security to manually set authentication
                    if (jwtTokenUtil.isValidToken(token, userDetails)) {
                        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.authorities
                        )

                        usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource()
                                .buildDetails(request)

                        // After setting the Authentication in the context, we specify that the current user is
                        // authenticated. So it passes the spring Security Configurations successfully.
                        SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                    }
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String")
            }
        } catch (e: IllegalArgumentException) {
            println("Unable to get JWT Token")
        } catch (e: ExpiredJwtException) {
            println("JWT token has expired")
        } finally {
            chain.doFilter(request, response)
        }
    }
}