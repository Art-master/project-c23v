package com.network.c23v;

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.server.adapter.WebHttpHandlerBuilder


@ComponentScan(basePackages = ["com.network.c23v"])
@EnableWebFlux
class Application

fun main(args: Array<String>) {
	AnnotationConfigApplicationContext(
		Application::class.java
	)
}
