package vincenzocostantini.yapiu.yapiuwebserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Utente;

@Repository
public interface UtenteRepository extends JpaRepository< Utente, String > {
}
