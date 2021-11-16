package com.core.app.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*


@Configuration
class Localization {

    @Bean
    fun localResolver(): LocaleResolver {
        val resolver = SessionLocaleResolver()
        resolver.setDefaultLocale(Locale.US)
        return resolver
    }

    @Bean
    fun localChangeInterceptor(): LocaleChangeInterceptor {
        val interceptor = LocaleChangeInterceptor()
        return interceptor
    }

    @Bean
    fun messageSource(): MessageSource? {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasenames("lang/messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}