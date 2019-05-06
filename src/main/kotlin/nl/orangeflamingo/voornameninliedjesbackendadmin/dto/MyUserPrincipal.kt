package nl.orangeflamingo.voornameninliedjesbackendadmin.dto

import nl.orangeflamingo.voornameninliedjesbackendadmin.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class MyUserPrincipal(
        val user: User
) : UserDetails {
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = listOf<GrantedAuthority>(SimpleGrantedAuthority("User"))
        return authorities.toMutableList()
    }
}