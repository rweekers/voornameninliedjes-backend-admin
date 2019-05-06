package nl.orangeflamingo.voornameninliedjesbackendadmin.repository

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?
}