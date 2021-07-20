package com.network.c23v

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.boot.web.embedded.netty.SslServerCustomizer
import org.springframework.boot.web.server.Http2
import org.springframework.boot.web.server.Ssl
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.stereotype.Component


@Component
class NettyWebServerFactorySslCustomizer :
    WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {
    override fun customize(serverFactory: NettyReactiveWebServerFactory) {
        val ssl = Ssl()
        ssl.isEnabled = true
        ssl.keyStore = "classpath:sample.jks"
        ssl.keyAlias = "alias"
        ssl.keyPassword = "password"
        ssl.keyStorePassword = "secret"
        val http2 = Http2()
        http2.isEnabled = false
        serverFactory.addServerCustomizers(SslServerCustomizer(ssl, http2, null))
        serverFactory.port = 8443
    }
}