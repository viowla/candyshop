package kz.iitu.candyshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "userRole"})
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;

    public User(){
        userRole = new ArrayList<>();
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "id")})
    private List<Role> userRole;

    @OneToMany(mappedBy = "users")
    private List<Order> orders;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRole;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void addUserRole(Role newUserRole){
        userRole.add(newUserRole);
    }

}
