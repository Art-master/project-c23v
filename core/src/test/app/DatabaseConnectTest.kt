package app

import com.gateway.app.Application
import com.core.app.repository.UserRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@EnableR2dbcRepositories("com.gateway.app.repository")
@EnableAutoConfiguration
@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@DataR2dbcTest
@Import(UserRepository::class)
@ExtendWith(SpringExtension::class)
class DatabaseConnectTest {

/*    @Autowired
    var client: DatabaseClient? = null*/

    @Autowired
    lateinit var userRepository: UserRepository

/*    @Autowired
    var template: R2dbcEntityTemplate? = null

    @Autowired
    private val databaseClient: DatabaseClient? = null

    @Autowired
    private val connectionFactory: ConnectionFactory? = null*/

    @Test
    fun testDatabaseClientExisted() {
        assertNotNull(userRepository)
    }
}