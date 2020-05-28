package kz.iitu.candyshop.controller;


import io.swagger.annotations.Api;
import kz.iitu.candyshop.entity.Role;
import kz.iitu.candyshop.entity.User;
import kz.iitu.candyshop.exceptions.NoRoleException;
import kz.iitu.candyshop.exceptions.UserNotFoundException;
import kz.iitu.candyshop.repository.RoleRepository;
import kz.iitu.candyshop.repository.UserRepository;
import kz.iitu.candyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
@Api(value = "User Controller class")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/role/{role}")
    public List<User> getAllUserByRole(@PathVariable String role){
        return userRepository.findAllByUserRoleContains(role);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id,
                           @RequestBody User user) {

        System.out.println("UserController.updateUser");
        System.out.println("id = " + id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getName() = " + authentication.getName());

        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @PostMapping("")
    public String createUser(User user, Map<String, Object> model) throws NoRoleException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findById((long) 2).get();
        user.addUserRole(userRole);
        userService.createUser(user);
        return "redirect:/login";
    }
}
