package DanielRoldanCS.SteamRandomGamesApp.service;

import DanielRoldanCS.SteamRandomGamesApp.exception.UserNotFoundException;
import DanielRoldanCS.SteamRandomGamesApp.model.User;
import DanielRoldanCS.SteamRandomGamesApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Add a new User
    public User addUser(User user){

        System.out.println(user);
        return userRepository.save(user);
    }

    //Get all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Get User by ID
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    //Get User by Email
    public List<User> getUserByEmail(String email){
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        List <User> users = userRepository.findByEmailContainingIgnoreCase(email);

        if(users.isEmpty()){
            throw new UserNotFoundException("No users were found with email: " + email);
        }

        return users;
    }

    //Get User by name
    public List<User> getUserByName(String name){
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        List<User> users = userRepository.findByNameContainingIgnoreCase(name);
        if(users.isEmpty()){
            throw new UserNotFoundException("No users were found with name: " + name);
        }

        return users;
    }

    //Delete User
    public void deleteUserById(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    // Update user
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }
}
