package com.core.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.PropertySource
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
//@EnableWebFluxSecurity
@EnableHypermediaSupport(type = [EnableHypermediaSupport.HypermediaType.HAL])
@EntityScan(basePackages = ["com.core.app.entities"])
@PropertySource(value = ["classpath:application.properties"], ignoreResourceNotFound = true)
@SpringBootApplication(scanBasePackages = ["com.core.app"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
