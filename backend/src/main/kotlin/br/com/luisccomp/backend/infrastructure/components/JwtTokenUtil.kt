package br.com.luisccomp.backend.infrastructure.components

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenUtil {

    private companion object {
        const val JWT_TOKEN_VALIDITY = 5L * 60L * 60L
    }

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun <T> getClaimFromToken(token: String, claimsResolver: (Claims) -> T): T =
            getAllClaimsFromToken(token).let(claimsResolver)


    /**
     * Extract UserName from JWT token. In this project, UserName is UserEmail.
     */
    fun getUserNameFromToken(token: String): String? =
            getClaimFromToken(token, Claims::getSubject)


    fun getExpirationDateFromToken(token: String): Date =
            getClaimFromToken(token, Claims::getExpiration)


    fun isValidToken(token: String, userDetails: UserDetails): Boolean =
            getUserNameFromToken(token) == userDetails.username && !isTokenExpired(token)


    fun generateToken(userDetails: UserDetails, claims: MutableMap<String, Any> = mutableMapOf()) =
            doGenerateToken(claims, userDetails.username)


    private fun getAllClaimsFromToken(token: String): Claims =
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .body


    private fun isTokenExpired(token: String): Boolean =
            getExpirationDateFromToken(token).before(Date())


    private fun doGenerateToken(claims: MutableMap<String, Any>, subject: String): String =
            Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(Date(System.currentTimeMillis()))
                    .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact()

}