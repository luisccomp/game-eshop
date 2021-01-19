package br.com.luisccomp.backend.controller

import br.com.luisccomp.backend.domain.model.response.user.UserAuthenticationResponse
import br.com.luisccomp.backend.infrastructure.components.JwtTokenUtil
import br.com.luisccomp.backend.repository.user.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import factory.UserFactoryTest.morganUser
import factory.UserFactoryTest.morganUserAuthenticationRequest
import factory.UserFactoryTest.morganUserEmail
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
internal class AuthenticationControllerTest(
        @Autowired val objectMapper: ObjectMapper,
        @Autowired val mvc: MockMvc,
        @Autowired val jwtTokenUtil: JwtTokenUtil
) {
    @Autowired
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        userRepository.save(morganUser)
    }

    @AfterEach
    fun tearDown() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("When send valid credentials, should authenticate a user and return a JWT token")
    fun userLoginTest() {
        val result = mvc.perform(
                post("/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(morganUserAuthenticationRequest))
        )
                .andExpect(status().isOk)
                .andReturn()

        val userAuthenticationResponse = objectMapper.readValue(result.response.contentAsString, UserAuthenticationResponse::class.java)

        assertEquals(jwtTokenUtil.getUserNameFromToken(userAuthenticationResponse.token), morganUserEmail)
    }

    @Test
    @DisplayName("When send invalid credentials, should return status UNAUTHORIZED")
    fun userLoginWithInvalidCredentials() {
        mvc.perform(
                post("/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(morganUserAuthenticationRequest).replace("#", ""))
        )
                .andExpect(status().isUnauthorized)
    }
}