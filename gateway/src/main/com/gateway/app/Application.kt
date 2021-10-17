package com.gateway.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.PropertySource
import org.springframework.transaction.annotation.EnableTransactionManagement
import reactor.netty.DisposableServer

@EnableTransactionManagement
@EntityScan(basePackages = ["com.gateway.app.entities"])
@PropertySource(value = ["classpath:application.properties"], ignoreResourceNotFound = true)
@SpringBootApplication(scanBasePackages = ["com.gateway.app"])
class Application

fun main(args: Array<String>) {
    AnnotationConfigApplicationContext(Application::class.java).use { context ->
        context.getBean(DisposableServer::class.java).onDispose().block()
    }
}