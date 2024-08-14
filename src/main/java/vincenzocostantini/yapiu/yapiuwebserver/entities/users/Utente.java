package vincenzocostantini.yapiu.yapiuwebserver.entities.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ruolo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ruolo")
public abstract class Utente {
    @Id
    private String email;

    private String password;
    private String nome;
    private String cognome;

    @Enumerated(EnumType.STRING)
    private ROLE ruolo;

    // Costruttore comune
    public Utente(String email, String password, String nome, String cognome, ROLE role) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = role;
    }

    public void setRole(ROLE role) {
        this.ruolo = role;
    }
    public abstract String getRoleDescription();
}

