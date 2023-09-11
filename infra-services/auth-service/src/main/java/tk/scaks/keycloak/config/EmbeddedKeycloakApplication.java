package tk.scaks.keycloak.config;

import org.keycloak.Config;
import org.keycloak.common.util.StringPropertyReplacer;
import org.keycloak.common.util.SystemEnvProperties;
import org.keycloak.exportimport.ExportImportManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.services.managers.ApplianceBootstrap;
import org.keycloak.services.managers.RealmManager;
import org.keycloak.services.resources.KeycloakApplication;
import org.keycloak.services.util.JsonConfigProviderFactory;
import org.keycloak.util.JsonSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class EmbeddedKeycloakApplication extends KeycloakApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedKeycloakApplication.class);

    static KeycloakServerProperties keycloakServerProperties;

    protected void loadConfig() {
        JsonConfigProviderFactory factory = new RegularJsonConfigProviderFactory();
        Config.init(factory.create()
            .orElseThrow(() -> new NoSuchElementException("No value present")));
    }

    @Override
    protected ExportImportManager bootstrap() {
        final ExportImportManager exportImportManager = super.bootstrap();
        createMasterRealmAdminUser();
        createScaksRealm();
        return exportImportManager;
    }

    private void createMasterRealmAdminUser() {

        KeycloakSession session = getSessionFactory().create();

        ApplianceBootstrap applianceBootstrap = new ApplianceBootstrap(session);

        KeycloakServerProperties.AdminUser admin = keycloakServerProperties.getAdminUser();

        try {
            session.getTransactionManager().begin();
            applianceBootstrap.createMasterRealmUser(admin.getUsername(), admin.getPassword());
            session.getTransactionManager().commit();
        } catch (Exception ex) {
            LOG.warn("Couldn't create keycloak master admin user: {}", ex.getMessage());
            session.getTransactionManager().rollback();
        }

        session.close();
    }

    private void createScaksRealm() {
        KeycloakSession session = getSessionFactory().create();

        try {
            session.getTransactionManager().begin();

            RealmManager manager = new RealmManager(session);

            RealmModel scaks = manager.getRealmByName("scaks");
            if (scaks == null) { // maybe already exist realm if using external DB
                Resource starterRealmImportFile = new ClassPathResource(keycloakServerProperties.getRealmImportFile());
                // Read realm to string
                String stringRealm = starterRealmImportFile.getContentAsString(StandardCharsets.UTF_8);
                // replace ${name} to ENV values in realm
                String realmWithEnv = StringPropertyReplacer.replaceProperties(stringRealm, new SystemEnvProperties(System.getenv()));

                RealmRepresentation realmRepresentation = JsonSerialization.readValue(realmWithEnv, RealmRepresentation.class);

                manager.importRealm(
                        realmRepresentation);

                session.getTransactionManager().commit();
            }
        } catch (Exception ex) {
            LOG.warn("Failed to import Realm json file: {}", ex.getMessage());
            session.getTransactionManager().rollback();
        }

        session.close();
    }
}
