package services

import com.network.app.entities.User
import com.network.app.repository.UserRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserService(val userRepository: UserRepository) {


    @PreAuthorize("hasRole('ADMIN')")
    fun initTestUsers(usersCount: Int) {

        val users = MutableList(usersCount) {
            User().apply {
                name = "user $it"
                phoneNumber = 791690571L + it
            }
        }

        with(userRepository) {
            saveAll(users)
                .map { it.id }
                .collect(Collectors.toList())
                .subscribe {
                    val mainUser = User().apply {
                         name = "Admin"
                        friendsIds = it
                    }
                    userRepository.save(mainUser)
                }
                .dispose()
        }
    }
}