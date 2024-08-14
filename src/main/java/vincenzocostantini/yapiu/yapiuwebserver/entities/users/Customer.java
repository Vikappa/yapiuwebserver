package vincenzocostantini.yapiu.yapiuwebserver.entities.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer extends Utente {

    public Customer(String email, String password, String nome, String cognome) {
        super(email, password, nome, cognome, ROLE.CUSTOMER);
    }

    public Customer() {
        super();
        this.setRole(ROLE.CUSTOMER);
    }

    @Override
    public String getRoleDescription() {
        return "This is a customer role.";
    }

}
