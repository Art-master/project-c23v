package com.network.c23v;

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.netty.DisposableServer
import reactor.netty.http.server.HttpServer


@ComponentScan(basePackages = ["com.network.c23v"])
@EnableWebFlux
class Application {
	@Bean
	fun disposableServer(context: ApplicationContext?): DisposableServer {
		val handler = WebHttpHandlerBuilder.applicationContext(context!!).build()
		val adapter = ReactorHttpHandlerAdapter(handler)
		val httpServer = HttpServer.create().host("localhost").port(8084)
		return httpServer.handle(adapter).bindNow()
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			AnnotationConfigApplicationContext(Application::class.java).use { context ->
				context.getBean(
					DisposableServer::class.java
				).onDispose().block()
			}
		}
	}
}
