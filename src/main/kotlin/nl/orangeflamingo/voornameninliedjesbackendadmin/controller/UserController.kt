package nl.orangeflamingo.voornameninliedjesbackendadmin.controller

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.User
import nl.orangeflamingo.voornameninliedjesbackendadmin.dto.UserDto
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class UserController {
    private val log = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @GetMapping("/users")
    fun getUsers(): List<UserDto> {
        return userRepository.findAll().map { convertToDto(it) }
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable("id") id: String): UserDto {
        val user = userRepository.findById(id).orElseThrow { RuntimeException("User with $id not found") }
        return convertToDto(user)
    }

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
