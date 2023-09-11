package tk.scaks.keycloak.controllers;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.ModelToRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.services.managers.RealmManager;
import org.keycloak.services.resources.KeycloakApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
//    Internal new client registration example
// Example creating user
//    @GetMapping("/reg")
//    public UserRepresentation createUser() {
//        KeycloakSession session = KeycloakApplication.getSessionFactory().create();
//        try {
//            session.getTransactionManager().begin();
//            RealmManager manager = new RealmManager(session);
//            RealmModel scaks = manager.getRealmByName("scaks");
//            UserModel testUser = session.users().addUser(scaks,"testUser");
//            testUser.setEnabled(true);
//            testUser.setEmailVerified(true);
//
//            UserCredentialModel password = UserCredentialModel.password("123");
//            testUser.credentialManager().updateCredential(password);
//            UserRepresentation userRepresentation = ModelToRepresentation.toRepresentation(session, scaks, testUser);
//            session.getTransactionManager().commit();
//
//            return userRepresentation;
//        } catch (Exception ex) {
//            System.out.println("Failed to import Realm json file: " + ex.getMessage());
//            session.getTransactionManager().rollback();
//            throw ex;
//        }
//    }
}
