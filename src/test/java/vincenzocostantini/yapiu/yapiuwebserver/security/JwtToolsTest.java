package vincenzocostantini.yapiu.yapiuwebserver.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.ROLE;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Utente;
import vincenzocostantini.yapiu.yapiuwebserver.payload.loginDTO.CustomerLoginDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(
        properties = {
                "jwt.secret=d80d5af689bb7c72b3a35eefedabc699d2aa374834f76b3d17e5823dee26f3eade7d59c9450d3ccc7782981030ecfed2163cc27a140dc647ac90ed125353872cc59d2ef4d92dba4369aef5c5b43240db072043ebb557a2756fdd28e8da7b2e9c00c69de61254145ae2c53cde1c20a47a1a0f35cc601c98e717c310c5de5c41120fc38a60a342599db82fabd81cdf58f977428c3f37d5ebd228d1458bb8d9c52df9ba058546822e42bedcba0f8fca7417fce3641216cdc3a70c06ec7d4491e1324a332c64408e5165966c1c7bb33e430419b4a9d63d594c95abd5fc35f044dfa85b5b945b10a4263e12a9ae856d03f11c264f62b547a1f090e72c6bdfa6c8af36"
        }
)
@ActiveProfiles("test")
class JwtToolsTest {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private JWTTools jwtTools;

    @Mock
    private Utente user;

    @Mock
    private CustomerLoginDTO customerLoginDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExtractTableNumber() {
        // Arrange
        when(customerLoginDTO.tavNum()).thenReturn(42);
        String token = jwtTools.createCustomerToken(customerLoginDTO);

        // Act
        int tableNumber = jwtTools.extractTableNumber(token);

        // Assert
        assertEquals(42, tableNumber);
    }

    @Test
    public void testCreateToken() {
        when(user.getEmail()).thenReturn("test@example.com");
        when(user.getRuolo()).thenReturn(ROLE.CUSTOMER);

        String token = jwtTools.createToken(user);

        assertNotNull(token);
    }
}
