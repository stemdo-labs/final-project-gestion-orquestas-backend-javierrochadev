package com.gruposei.gestion_orquestas.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.gruposei.gestion_orquestas.model.Role;
import com.gruposei.gestion_orquestas.model.User;
import com.gruposei.gestion_orquestas.service.MeetingMinuteService;
import com.gruposei.gestion_orquestas.service.RoleService;
import com.gruposei.gestion_orquestas.service.UserService;

@Component
@Order(1)
public class UsersByDefault implements CommandLineRunner{

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MeetingMinuteService meetingMinuteService;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setUsername("david");
        user1.setPassword(bCryptPasswordEncoder.encode("123"));
        user1.setName("David");
        user1.setLastname("Boffelli");
        user1.setEmail("davidboffelli@yahoo.com");
        user1.setAddress("Por acá");
//        user.setEnabled(true);
        userService.create(user1);

        User user4 = new User();
        user4.setUsername("damian");
        user4.setPassword(bCryptPasswordEncoder.encode("123"));
        user4.setName("Damian");
        user4.setLastname("Ciancio");
        user4.setEmail("damianciancio7@gmail.com");
        user4.setAddress("Riobamba y Paraguay");
//        user.setEnabled(true);
        userService.create(user4);

        User user2 = new User();
        user2.setUsername("guest");
        user2.setPassword(bCryptPasswordEncoder.encode("123"));
        user2.setName("Guest");
        user2.setLastname("Invitado");
        user2.setEmail("guest@yahoo.com");
        user2.setAddress("En tu mente");
//        user.setEnabled(true);
        userService.create(user2);

        User user3 = new User();
        user3.setId(1L);
        user3.setUsername("admin");
        user3.setPassword(bCryptPasswordEncoder.encode("admin"));
        user3.setName("Admin");
        user3.setLastname("Admin");
        user3.setEmail("gruposei@yahoo.com");
        user3.setAddress("Donde vendan empanadas piolas");
//        user.setEnabled(true);

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("Administradores de Sistema");
        role1.setEnabled(true);
        roleService.create(role1);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("Integrante de la Orquesta");
        role2.setEnabled(true);
        roleService.create(role2);

        Role role3 = new Role();
        role3.setId(3L);
        role3.setName("Visitante");
        role3.setEnabled(false);
        roleService.create(role3);

        user3.addRole(role1);
        userService.create(user3);

        user4.addRole(role3);
        userService.create(user4);
    }
}