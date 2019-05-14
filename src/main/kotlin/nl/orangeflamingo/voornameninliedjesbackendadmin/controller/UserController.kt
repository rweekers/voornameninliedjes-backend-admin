package nl.orangeflamingo.voornameninliedjesbackendadmin.controller

import nl.orangeflamingo.voornameninliedjesbackendadmin.Utils.Companion.ROLE_OWNER
import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.User
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.UserDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class UserController {
    private val log = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Secured(ROLE_OWNER)
    @GetMapping("/users")
    fun getUsers(): List<UserDto> {
        return userRepository.findAll().map { convertToDto(it) }
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody user: UserDto): ResponseEntity<UserDto> {
        val dbUser = userRepository.findByUsername(username = user.username)?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        val loginSuccessful = passwordEncoder.matches(user.password, dbUser.password)
        if (!loginSuccessful) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        return ResponseEntity.of(Optional.of(UserDto(
                id = dbUser.id,
                username = dbUser.username,
                password = user.password,
                roles = dbUser.roles)
        ))
    }

    @Secured(ROLE_OWNER)
    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable("id") id: String): UserDto {
        val user = userRepository.findById(id).orElseThrow { RuntimeException("User with $id not found") }
        return convertToDto(user)
    }

    @Secured(ROLE_OWNER)
    @PostMapping("/users")
    fun newUser(@RequestBody newUser: UserDto): User {
        log.info("Creating new user ${newUser.username}")
        val user = convert(newUser)
        return userRepository.save(user)
    }

    private fun convert(userDto: UserDto): User {
        return User(userDto.id, userDto.username, passwordEncoder.encode(userDto.password), userDto.roles)
    }

    private fun convertToDto(user: User): UserDto {
        return UserDto(user.id, user.username, user.password, user.roles)
    }
}
