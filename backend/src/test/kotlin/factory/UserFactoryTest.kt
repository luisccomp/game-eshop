package factory

import br.com.luisccomp.backend.domain.model.entity.user.User
import br.com.luisccomp.backend.domain.model.request.user.UserAuthenticationRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object UserFactoryTest {
    val morganUserAuthenticationRequestEmail = "morgan@gmail.com"
    val morganUserAuthenticationRequestPassword = "Freeman@2020#"

    val morganUserAuthenticationRequest = UserAuthenticationRequest(
            email = morganUserAuthenticationRequestEmail,
            password = morganUserAuthenticationRequestPassword
    )

    val morganUserId = 1L
    val morganUserFirstName = "Morgan"
    val morganUserLastName = "Freeman"
    val morganUserEmail = "morgan@gmail.com"
    val morganUserPassword = BCryptPasswordEncoder().encode("Freeman@2020#")

    val morganUser = User(
            id = morganUserId,
            firstName = morganUserFirstName,
            lastName = morganUserLastName,
            email = morganUserEmail,
            password = morganUserPassword
    )
}