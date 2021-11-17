package com.core.app.extensions

import org.springframework.context.support.ResourceBundleMessageSource
import java.util.*

fun ResourceBundleMessageSource.getMessage(code: String, locale: Locale): String {
    return this.getMessage(code, emptyArray(), locale)
}

fun ResourceBundleMessageSource.getMessage(code: String, localeTag: String): String {
    val locale = Locale(localeTag)
    return this.getMessage(code, emptyArray(), locale)
}