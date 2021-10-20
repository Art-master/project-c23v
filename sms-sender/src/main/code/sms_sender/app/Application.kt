package code.sms_sender.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.PropertySource
import org.springframework.transaction.annotation.EnableTransactionManagement


@EnableTransactionManagement
@EntityScan(basePackages = ["code.sms_sender.app.entities"])
@PropertySource(value = ["classpath:application.properties"], ignoreResourceNotFound = true)
@SpringBootApplication(scanBasePackages = ["code.sms_sender.app"])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
