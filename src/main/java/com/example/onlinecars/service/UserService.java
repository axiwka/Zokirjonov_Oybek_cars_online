package com.example.onlinecars.service;

import com.example.onlinecars.dto.UserDto;
import com.example.onlinecars.model.Role;
import com.example.onlinecars.model.Users;
import com.example.onlinecars.respository.RoleRepo;
import com.example.onlinecars.respository.UserRepo;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final RoleRepo roleRepo;

    public List<Users> getAll(){
        return repo.findAll();
    }
    public Users getUSerById(Integer id ){
        Optional<Users> exist = repo.findById(id);
        if (exist.isPresent()) {
            return exist.get();
        }else throw  new IllegalArgumentException("Not found");
    }

    public void save(UserDto userDto){
        List<Role> roles = List.of(roleRepo.findRoleByRoleName("ROLE_USER"));
        Users users1 = Users.builder()
                .fullName(userDto.getFullName())
                .password(encoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .roles(roles)
                .build();
        repo.save(users1);
    }

    public void deleteUser(Integer id ){
        Optional<Users> exist = repo.findById(id);
        if (exist.isPresent()) {
            repo.deleteById(id);
        }else throw new IllegalArgumentException("Could not  delete");
    }
    public void editUser(Users users){
        Optional<Users> exist = repo.findById(users.getId());
        if (exist.isPresent()) {
            repo.save(users);
        }else throw new IllegalArgumentException("User not found");
    }

    @Override
    public Users loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        System.out.println(phoneNumber);
        Users user = repo.findByPhoneNumber(phoneNumber).orElseThrow(() -> new IllegalStateException("User not found"));
        System.out.println(user);
        return user;
    }


    public List<Users> pagination(int page,int size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Users> users = repo.findAll(pageable);
        int totalPages = users.getTotalPages();
        long totalElement = users.getTotalElements();
        List<Users> usersList = users.getContent();
        return usersList;
    }
}
