package com.example.onlinecars.service;

import com.example.onlinecars.model.Role;
import com.example.onlinecars.respository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }
    public Role getRoleById(Integer id ){
        Optional<Role> exist = roleRepo.findById(id);
        if (exist.isPresent()) {
            return exist.get();
        }else throw  new IllegalArgumentException("Not found");
    }

    public void saveRole(Role role){
        roleRepo.save(role);
    }

    public void deleteRole(Integer id ){
        Optional<Role> exist = roleRepo.findById(id);
        if (exist.isPresent()) {
            roleRepo.deleteById(id);
        }else throw new IllegalArgumentException("Could not  delete");
    }
    public void editRole(Role role){
        Optional<Role> exist = roleRepo.findById(role.getId());
        if (exist.isPresent()) {
            roleRepo.save(role);
        }else throw new IllegalArgumentException("Role not found");
    }
}
