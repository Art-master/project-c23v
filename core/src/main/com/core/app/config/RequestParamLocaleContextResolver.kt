package com.core.app.config

import org.springframework.context.i18n.LocaleContext
import org.springframework.context.i18n.SimpleLocaleContext
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.i18n.LocaleContextResolver
import java.util.*

class RequestParamLocaleContextResolver : LocaleContextResolver {

    override fun resolveLocaleContext(exchange: ServerWebExchange): LocaleContext {
        var targetLocale = Locale.getDefault()
        val referLang = exchange.request.queryParams["lang"]
        if (referLang != null && referLang.isNotEmpty()) {
            val lang = referLang[0]
            targetLocale = Locale.forLanguageTag(lang)
        }
        return SimpleLocaleContext(targetLocale)
    }

    override fun setLocaleContext(exchange: ServerWebExchange, localeContext: LocaleContext?) {
        throw UnsupportedOperationException("Not Supported");
    }
}