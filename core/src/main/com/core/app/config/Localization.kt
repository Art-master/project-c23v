package com.core.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@Configuration
class Localization {

    @Bean
    fun localResolver() : SessionLocaleResolver {
        val resolver = SessionLocaleResolver()
        resolver.setDefaultLocale(Locale.getDefault())
        return resolver
    }
}