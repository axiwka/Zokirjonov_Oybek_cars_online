package com.example.onlinecars;

import com.example.onlinecars.model.Colour;
import com.example.onlinecars.model.Role;
import com.example.onlinecars.model.Users;
import com.example.onlinecars.projection.ColourProjection;
import com.example.onlinecars.respository.ColourRepo;
import com.example.onlinecars.respository.RoleRepo;
import com.example.onlinecars.respository.UserRepo;
import com.example.onlinecars.service.RoleService;
import com.example.onlinecars.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder encoder;
    private final ColourRepo colourRepo;

    @Value("${spring.sql.init.mode}")
    private String initmode;


    @Override
    public void run(String... args) throws Exception {
        if (initmode.equals("always")) {
            roleRepo.save(Role.builder().id(1)
                    .roleName("ROLE_SUPER_ADMIN").
                    build());
            roleRepo.save(Role.builder().id(2)
                    .roleName("ROLE_ADMIN").
                    build());
            roleRepo.save(Role.builder().id(3)
                    .roleName("ROLE_USER").
                    build());
            List<Role> list = roleRepo.findAll();
            System.out.println(list);
            userRepo.save(Users.builder()
                    .id(1)
                    .fullName("Zokirjonov Oybek")
                    .phoneNumber("+998946173735")
                    .password(encoder.encode("axiwka123"))
                    .roles(list)
                    .build());

//            COLOR
            colourRepo.save(new Colour("Black"));
            colourRepo.save(new Colour("White"));

        }
    }

}
