package vincenzocostantini.yapiu.yapiuwebserver.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource(value = "file:./env.properties", ignoreResourceNotFound = true)
public class AppPropertiesConfiguration {
}



