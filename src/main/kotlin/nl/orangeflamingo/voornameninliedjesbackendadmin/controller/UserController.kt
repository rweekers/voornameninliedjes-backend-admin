package nl.orangeflamingo.voornameninliedjesbackendadmin.controller

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.User
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.UserDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.generic.InvalidCredentialsException
import nl.orangeflamingo.voornameninliedjesbackendadmin.generic.ResponseError
import nl.orangeflamingo.voornameninliedjesbackendadmin.generic.UserNotFoundException
import nl.orangeflamingo.voornameninliedjesbackendadmin.generic.Utils.Companion.INVALID_CREDENTIALS
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @GetMapping("/users")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun getUsers(): List<UserDto> {
        log.info("Requesting all users")
        return userRepository.findAll().map { convertToDto(it) }
    }

    @PostMapping("/authenticate")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun authenticate(@RequestBody user: UserDto): ResponseEntity<UserDto> {
        log.info("Authenticating user ${user.username}")
        val dbUser = userRepository.findByUsername(username = user.username)
            ?: throw InvalidCredentialsException(INVALID_CREDENTIALS)
        if (!passwordEncoder.matches(user.password, dbUser.password)) {
            throw InvalidCredentialsException(INVALID_CREDENTIALS)
        }

        return ResponseEntity.of(
            Optional.of(
                UserDto(
                    id = dbUser.id,
                    username = dbUser.username,
                    password = user.password,
                    roles = dbUser.roles
                )
            )
        )
    }

    @ExceptionHandler(InvalidCredentialsException::class)
    fun handleInvalidCredentialsException(exception: InvalidCredentialsException): ResponseEntity<ResponseError> {
        return ResponseEntity(ResponseError(exception.message ?: "Onbekende fout"), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(exception: UserNotFoundException): ResponseEntity<ResponseError> {
        return ResponseEntity(ResponseError(exception.message ?: "Onbekende fout"), HttpStatus.BAD_REQUEST)
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @GetMapping("/users/{id}")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun getUserById(@PathVariable("id") id: String): UserDto {
        log.info("Requesting user with id $id")
        val user = userRepository.findById(id).orElseThrow { UserNotFoundException("User with $id not found") }
        return convertToDto(user)
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PostMapping("/users")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun newUser(@RequestBody newUser: UserDto): User {
        log.info("Requesting creation of new user ${newUser.username}")
        val user = convert(newUser)
        return userRepository.save(user)
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @DeleteMapping("/users/{userId}")
    @CrossOrigin(origins = ["http://localhost:3000", "https://voornameninliedjes.nl", "*"])
    fun deleteUser(@PathVariable userId: String) {
        log.info("Deleting user with id $userId")
        userRepository.deleteById(userId)
    }

    private fun convert(userDto: UserDto): User {
        return User(userDto.id, userDto.username, passwordEncoder.encode(userDto.password), userDto.roles)
    }

    private fun convertToDto(user: User): UserDto {
        return UserDto(user.id, user.username, user.password, user.roles)
    }
}
