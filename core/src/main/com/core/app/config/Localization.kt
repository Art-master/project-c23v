package com.core.app.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration
import org.springframework.web.server.i18n.LocaleContextResolver


@Configuration
class Localization : DelegatingWebFluxConfiguration() {

    override fun createLocaleContextResolver(): LocaleContextResolver {
        return RequestParamLocaleContextResolver()
    }

    @Bean
    fun messageSource(): MessageSource? {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasenames("lang/messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}