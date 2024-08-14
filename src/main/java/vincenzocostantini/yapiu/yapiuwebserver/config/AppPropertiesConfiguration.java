package vincenzocostantini.yapiu.yapiuwebserver.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource("file:env.properties")
public class AppPropertiesConfiguration {

    @Value("${YAPIUDATABASE_URL}")
    private String databaseUrl;

    // altri @Value se necessario

}


