package kz.iitu.candyshop.controller;

import io.swagger.annotations.Api;
import kz.iitu.candyshop.entity.Role;
import kz.iitu.candyshop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Api(value = "Role Controller class")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @PostMapping("/add")
    public Role addRole(@RequestBody Role userRole){
        return roleRepository.saveAndFlush(userRole);
    }


    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id){
        roleRepository.deleteById(id);
    }

}
