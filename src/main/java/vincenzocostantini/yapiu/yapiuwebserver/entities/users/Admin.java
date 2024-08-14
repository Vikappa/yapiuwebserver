package vincenzocostantini.yapiu.yapiuwebserver.entities.users;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends Utente {

    public Admin(String email, String password, String nome, String cognome) {
        super(email, password, nome, cognome, ROLE.ADMIN);
    }

    public Admin() {
        super();
        this.setRole(ROLE.ADMIN);
    }

    @Override
    public String getRoleDescription() {
        return "This is an admin role.";
    }

}
