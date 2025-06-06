package net.sepidan.loyalty.utils;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KeycloakUtil {

  @Value("${iam.url}")
  private String baseUrl;

  @Value("${iam.realm}")
  private String realm;

  @Value("${iam.client-id}")
  private String clientId;

  @Value("${iam.client-secret}")
  private String clientSecret;

  public void createUser() throws Exception {
//    CredentialRepresentation credential = new CredentialRepresentation();
//    credential.setType(CredentialRepresentation.PASSWORD);
//    credential.setValue();
//
//    Map<String, List<String>> attributes = new HashMap<>();
//    attributes.put("phoneNumber", List.of(user.getPhone()));
//
//    UserRepresentation userRepresentation = new UserRepresentation();
//    userRepresentation.setUsername();
//    userRepresentation.setFirstName();
//    userRepresentation.setLastName();
//    userRepresentation.setCredentials(List.of(credential));
//    userRepresentation.setAttributes(attributes);
//    userRepresentation.setEnabled(true);

//    try {
//      RealmResource realmResource = getKeycloakBuilder().build()
//          .realm(realm);
//
//      Response response = realmResource.users().create(userRepresentation);
//      if (response.getStatus() == HttpResponseCodes.SC_CREATED) {
//        log.info("User created successfully");
//
//        // Fetch the created user's ID
//        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
//
//        // Assign role to the user
//        assignRoleToUser(realmResource, userId, "customer");
//      } else {
//        log.error("Failed to create user: " + response.getStatusInfo());
//      }
//
//
//    } catch (Exception exception) {
//      log.error(exception.getMessage());
//    }
  }

  private void assignRoleToUser(RealmResource realmResource, String userId, String roleName) {
    // Fetch the roles resource
    RolesResource rolesResource = realmResource.roles();
    RoleRepresentation roleRepresentation = rolesResource.get(roleName).toRepresentation();

    // Assign the role to the user
    realmResource.users().get(userId).roles().realmLevel().add(List.of(roleRepresentation));
  }
}
