package com.network.c23v.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.netty.DisposableServer
import reactor.netty.http.server.HttpServer

@Configuration
@EnableWebFlux
class WebConfig {

    @Value("\${server.port:8080}")
    private val port = 8080

    @Bean
    fun disposableServer(context: ApplicationContext): DisposableServer {
        val handler = WebHttpHandlerBuilder.applicationContext(context).build()
        val adapter = ReactorHttpHandlerAdapter(handler)
        val httpServer = HttpServer.create().host("localhost").port(port)
        return httpServer.handle(adapter).bindNow()
    }
}