package vincenzocostantini.yapiu.yapiuwebserver.runners;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Admin;
import vincenzocostantini.yapiu.yapiuwebserver.repository.AdminRepository;

import static org.mockito.Mockito.*;

class DatabaseInitializerTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private DatabaseInitializer databaseInitializer;

    @Value("${admin.email}")
    private String adminEmail = "vincenzo.e.costantini@gmail.com";

    @Value("${admin.hashedPassword}")
    private String adminHash = "713BFDA78870BF9D1B261F565286F85E97EE614EFE5F0FAF7C34E7CA4F65BACA";

    @Value("${admin.nome}")
    private String adminFirstName = "Vincenzo";

    @Value("${admin.lastname}")
    private String adminLastName = "Costantini";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRunAfterStartup_whenNoAdminsExist_createsAdmin() {
        // Simula il comportamento del repository
        when(adminRepository.count()).thenReturn(0L);

        // Esegui il metodo
        databaseInitializer.runAfterStartup();

        // Verifica che il metodo save sia stato chiamato con il giusto oggetto Admin
        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    void testRunAfterStartup_whenAdminsExist_doesNotCreateAdmin() {
        // Simula il comportamento del repository
        when(adminRepository.count()).thenReturn(1L);

        // Esegui il metodo
        databaseInitializer.runAfterStartup();

        // Verifica che il metodo save non sia stato chiamato
        verify(adminRepository, times(0)).save(any(Admin.class));
    }
}
