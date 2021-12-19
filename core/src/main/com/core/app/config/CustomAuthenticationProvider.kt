package com.core.app.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails

class CustomAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {

    override fun additionalAuthenticationChecks(
        userDetails: UserDetails?,
        authentication: UsernamePasswordAuthenticationToken?
    ) {
        TODO("Not yet implemented")
    }

    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails {
        TODO("Not yet implemented")
    }

}