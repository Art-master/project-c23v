package com.network.c23v

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.http.server.reactive.HttpHandler
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.stereotype.Component
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.netty.http.server.HttpServer

/*
@Component
class NettyContext {
    @Bean
    fun nettyContext(context: ApplicationContext?): NettyContext {
        val handler: HttpHandler = WebHttpHandlerBuilder
            .applicationContext(context!!).build()
        val adapter = ReactorHttpHandlerAdapter(handler)
        val httpServer: HttpServer = HttpServer.create()
        httpServer.host("localhost")
        httpServer.port(8080)
        return httpServer.handle(adapter).configuration().
    }

}*/
