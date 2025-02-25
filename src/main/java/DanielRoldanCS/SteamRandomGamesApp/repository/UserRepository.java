package DanielRoldanCS.SteamRandomGamesApp.repository;

import DanielRoldanCS.SteamRandomGamesApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Finds the users with an email containing the string
    List<User> findByEmailContainingIgnoreCase(String email);

    //Finds the users with a name containing the string
    List<User> findByNameContainingIgnoreCase(String name);

}
