package vincenzocostantini.yapiu.yapiuwebserver.runners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Admin;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Utente;
import vincenzocostantini.yapiu.yapiuwebserver.repository.UtenteRepository;

@Component
public class DatabaseInitializer {

    @Autowired
    private UtenteRepository UtenteRepository;
    public DatabaseInitializer(UtenteRepository UtenteRepository) {

    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("Applicazione avviata completamente. Inizializzazione del database in corso...");

        if(UtenteRepository.count() == 0) {
            Utente admin = new Admin( );
        }
//        UtenteRepository.save(new Utente("<EMAIL>", "12345678", "Vincenzo", "Costantini", "ROLE_ADMIN"));

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
