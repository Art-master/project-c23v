package app

import com.network.app.Application
import com.network.app.repository.UserRepository
import io.r2dbc.spi.ConnectionFactory
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@EnableR2dbcRepositories("com.network.app.repository")
@EnableAutoConfiguration
@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@DataR2dbcTest
@Import(UserRepository::class)
class DatabaseConnectTest {

    @Autowired
    var client: DatabaseClient? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var template: R2dbcEntityTemplate? = null

    @Autowired
    private val databaseClient: DatabaseClient? = null

    @Autowired
    private val connectionFactory: ConnectionFactory? = null

    @Test
    fun testDatabaseClientExisted() {
        assertNotNull(userRepository)
    }
}