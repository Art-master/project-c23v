package com.gateway.app.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "server.custom")
class ServerProperties {
    /**
     * The url to connect to.
     */
    var enableSecurity: Boolean? = false
}