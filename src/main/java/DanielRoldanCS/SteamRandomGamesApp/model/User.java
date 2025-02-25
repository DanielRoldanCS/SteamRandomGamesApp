package DanielRoldanCS.SteamRandomGamesApp.model;

import jakarta.persistence.*;

@Entity
@Table(name="users_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public User(){
        this.name="Default";
        this.email="Default";
    }

    public User(String name, String email){
        this.name= name;
        this.email= email;
    }

    public void setId(Long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
