package com.network.config

import com.network.c23v.Application
import com.network.c23v.repository.UserRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.springframework.test.web.reactive.server.WebTestClient

@DataR2dbcTest
@ExtendWith(SpringExtension::class)
class DatabaseConnectTest {

    @Autowired
    var client: DatabaseClient? = null

/*    @Autowired
    var userRepository: UserRepository? = null*/

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun testDatabaseClientExisted() {
        assertNotNull(client)
    }
}