package vincenzocostantini.yapiu.yapiuwebserver.runners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Admin;
import vincenzocostantini.yapiu.yapiuwebserver.repository.AdminRepository;

@Component
public class DatabaseInitializer {


    @Autowired
    private AdminRepository adminRepository;

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.hashedPassword}")
    private String adminHash;
    @Value("${admin.nome}")
    private String adminFirstName;
    @Value("${admin.lastname}")
    private String adminLastName;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("Applicazione avviata completamente. Inizializzazione del database in corso...");

        if (adminRepository.count() == 0) {
            System.out.println("Nessun utente presente nel database. Creazione di un admin...");
            System.out.println("adminEmail: " + adminEmail);
            System.out.println("adminHash: " + adminHash);
            System.out.println("adminFirstName: " + adminFirstName);
            System.out.println("adminLastName: " + adminLastName);
            Admin admin = new Admin(adminEmail, adminHash, adminFirstName, adminLastName);
            adminRepository.save(admin);
            System.out.println("Admin creato con successo!");
        }

    }
}

//enum GINFLAVOUR {
//    SECCO, SPEZIATO, FRUTTATO, FLOREALE
//}
//enum FLAVOURS {
//    DOLCE, SALATO, SPEZIATO, FRUTTATO, FLOREALE, AMARO, SECCO;
//}
//
//enum COLORS_GUARNIZIONI {
//    ROSSO, ROSA, GIALLO, BLU, VERDE, GIALLO_SCURO, TRASPARENTE;
//}
//
//enum BASE_GIN_BRANDS {
//    SCHWEPPES, FEVER_TREE, CANADA_DRY, SPECTACULAR_Q;
//}
