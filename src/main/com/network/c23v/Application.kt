package com.network.c23v;

import org.springframework.context.annotation.*
import reactor.netty.DisposableServer

@ComponentScan(basePackages = ["com.network.c23v"])
@Configuration
@PropertySource(value = ["classpath:application.properties"], ignoreResourceNotFound = true)
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
