package br.com.luisccomp.backend.service.authentication

import br.com.luisccomp.backend.domain.model.entity.extension.entity.user.toUserDetails
import br.com.luisccomp.backend.exception.UnauthorizedException
import br.com.luisccomp.backend.infrastructure.components.JwtTokenUtil
import br.com.luisccomp.backend.service.authentication.impl.AuthenticationServiceImpl
import factory.UserFactoryTest.morganUser
import factory.UserFactoryTest.morganUserAuthenticationRequest
import factory.UserFactoryTest.morganUserAuthenticationRequestEmail
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [JwtTokenUtil::class])
@ActiveProfiles(value = ["test"])
class AuthenticationServiceTest {

    @MockBean
    lateinit var userDetailsService: UserDetailsService

    @MockBean
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    private lateinit var authenticationService: AuthenticationService

    companion object {
        fun <T> any(): T = Mockito.any()
    }

    @BeforeEach
    fun setUp() {
        authenticationService = AuthenticationServiceImpl(userDetailsService, jwtTokenUtil, authenticationManager)
    }

    @Test
    @DisplayName("Should authenticate a user when given valid login credentials")
    fun loginTest() {
        given(userDetailsService.loadUserByUsername(morganUserAuthenticationRequestEmail))
                .willReturn(morganUser.toUserDetails())

        val userAuthenticationResponse = authenticationService.login(morganUserAuthenticationRequest)

        assertEquals(morganUserAuthenticationRequestEmail, jwtTokenUtil.getUserNameFromToken(userAuthenticationResponse.token))
    }

    @Test
    @DisplayName("Should throw an error when try to authenticate with bad credentials")
    fun loginWithWrongUsernameTest() {
        given(authenticationManager.authenticate(any()))
                .willThrow(BadCredentialsException("Username or password are incorrect"))

        val throwable = catchThrowable { authenticationService.login(morganUserAuthenticationRequest) }

        assertTrue(throwable is UnauthorizedException)
        assertEquals("Invalid username or password", throwable.message)
    }

}