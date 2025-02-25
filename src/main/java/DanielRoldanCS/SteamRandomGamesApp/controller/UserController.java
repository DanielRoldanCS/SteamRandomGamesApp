package DanielRoldanCS.SteamRandomGamesApp.controller;

import DanielRoldanCS.SteamRandomGamesApp.model.User;
import DanielRoldanCS.SteamRandomGamesApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    //Create a new user
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    //Get all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User userFound = userService.getUserById(id);
        return ResponseEntity.ok(userFound);
    }

    //Searches by either email or name case-insensitive
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam(required = false) String email,
                                                 @RequestParam(required = false) String name) {
        List<User> users;
        if (name != null) {
            users = userService.getUserByName(name);
        } else if (email != null) {
            users = userService.getUserByEmail(email);
        } else {
            users = userService.getAllUsers();
        }
        return ResponseEntity.ok(users);
    }


    //Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByUserId(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

}
