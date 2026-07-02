package org.psychohelp.psychohelp.config;

import org.psychohelp.psychohelp.entity.Admin;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.repository.AdminRepository;
import org.psychohelp.psychohelp.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsererAdmin {

    @Bean
    CommandLineRunner initDatabase(AdminRepository adminRepository) {
        return args -> {
            // On vérifie si un admin existe déjà pour éviter les doublons
            if (adminRepository.findAll().isEmpty()) {

                Admin admin = new Admin();
                admin.setNom("Coulibaly");
                admin.setPrenom("Mohamed");
                admin.setMotDePasse("1234");
                admin.setMail("mc@gmail.com");
                admin.setTelephone("123456789");
                admin.setRole(RoleEnum.ADMIN);

                adminRepository.save(admin);
                System.out.println("--> Administrateur par défaut créé avec succès !");
            } else {
                System.out.println("--> Un administrateur existe déjà.");
            }
        };
    }
}