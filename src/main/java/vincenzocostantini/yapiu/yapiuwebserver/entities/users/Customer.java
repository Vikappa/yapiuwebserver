package vincenzocostantini.yapiu.yapiuwebserver.entities.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
