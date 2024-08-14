package vincenzocostantini.yapiu.yapiuwebserver.entities.users;

import jakarta.persistence.Entity;

@Entity
public class Employer extends Utente {

    public Employer(String email, String password, String nome, String cognome) {
        super(email, password, nome, cognome, ROLE.BARMAN);
    }

    public Employer() {
        super();
        this.setRole(ROLE.BARMAN);
    }

    @Override
    public String getRoleDescription() {
        return "This is a barman role.";
    }

}
