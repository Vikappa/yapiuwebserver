package vincenzocostantini.yapiu.yapiuwebserver.entities.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vincenzocostantini.yapiu.yapiuwebserver.entities.caricoscarico.Carico;
import vincenzocostantini.yapiu.yapiuwebserver.entities.caricoscarico.Scarico;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "admin")
public class Admin extends Utente {

    private ArrayList<Carico> carichi;
    private ArrayList<Scarico> scarichi;

    public Admin(String email, String password, String nome, String cognome) {
        super(email, password, nome, cognome, ROLE.ADMIN);
    }

    public Admin() {
        super();
        this.setRole(ROLE.ADMIN);
        this.carichi = new ArrayList<>();
        this.scarichi = new ArrayList<>();
    }

    @Override
    public String getRoleDescription() {
        return "This is an admin role.";
    }

}
