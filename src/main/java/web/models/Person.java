package web.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 2, max = 30, message = "Password should be between 2 and 30 characters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Person() {

    }

    public Person(int id, @NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String name, @NotEmpty(message = "Password should not be empty") @Size(min = 2, max = 30, message = "Password should be between 2 and 30 characters") String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //без паблика не работает
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getPeople().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getPeople().remove(this);
    }
}
