package tk.scaks.keycloak.config;

import org.keycloak.common.util.SystemEnvProperties;
import org.keycloak.services.util.JsonConfigProviderFactory;

import java.util.Properties;

public class RegularJsonConfigProviderFactory extends JsonConfigProviderFactory {

    // Override ENV properties!
    @Override
    protected Properties getProperties() {
        return new SystemEnvProperties(System.getenv());
    }
}
