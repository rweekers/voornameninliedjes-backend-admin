package nl.orangeflamingo.voornameninliedjesbackendadmin.dto

data class UserDto(

        val id: String? = null,

        val username: String,

        val password: String,

        val roles: MutableSet<String>
)