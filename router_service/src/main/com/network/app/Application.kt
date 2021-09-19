package com.network.app;

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.*
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import reactor.netty.DisposableServer


@Configuration
@EnableTransactionManagement
@PropertySource(value = ["classpath:application.properties"], ignoreResourceNotFound = true)
@EnableR2dbcRepositories("com.network.app.repository")
@EntityScan("com.network.app.entities")
@ComponentScan("com.network.app")
class Application {

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			AnnotationConfigApplicationContext(Application::class.java).use { context ->
				context.getBean(DisposableServer::class.java).onDispose().block()
			}
		}
	}
}
