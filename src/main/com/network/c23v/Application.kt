package com.network.c23v;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.*
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import reactor.netty.DisposableServer


@Configuration
//@EnableAutoConfiguration
@EnableTransactionManagement
@PropertySource(value = ["classpath:application.properties"], ignoreResourceNotFound = true)
@EnableR2dbcRepositories("com.network.c23v.repository")
//@EntityScan("com.network.c23v.repository")
@ComponentScan(basePackages = ["com.network.c23v"])
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
