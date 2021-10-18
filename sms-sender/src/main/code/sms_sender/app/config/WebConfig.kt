package code.sms_sender.app.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.audit.AuditEventRepository
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository
import org.springframework.boot.actuate.trace.http.HttpTraceRepository
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository
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

    @Value("\${server.port}")
    private val port = 8084

    @Value("\${server.address}")
    private val host = "localhost"

    @Bean
    fun disposableServer(context: ApplicationContext): DisposableServer {
        val handler = WebHttpHandlerBuilder.applicationContext(context).build()
        val adapter = ReactorHttpHandlerAdapter(handler)
        val httpServer = HttpServer.create().host(host).port(port)
        return httpServer.handle(adapter).bindNow()
    }

    @Bean
    fun httpTraceRepository(): HttpTraceRepository? {
        return InMemoryHttpTraceRepository()
    }

    @Bean
    fun auditEventRepository(): AuditEventRepository? {
        return InMemoryAuditEventRepository()
    }
}