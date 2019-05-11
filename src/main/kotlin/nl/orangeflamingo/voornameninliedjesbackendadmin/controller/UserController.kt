package nl.orangeflamingo.voornameninliedjesbackendadmin.controller

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.User
import nl.orangeflamingo.voornameninliedjesbackendadmin.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/owner")
class UserController {
    private val log = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    private lateinit var userRepository: UserRepository

    @GetMapping("/users")
    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable("id") id: String): User {
        return userRepository.findById(id).orElseThrow { RuntimeException("User with $id not found") }
    }

    @PostMapping("/users")
    fun newUser(@RequestBody newUser: User): User {
        log.info("Creating new user ${newUser.username}")
        return userRepository.save(newUser)
    }
}
