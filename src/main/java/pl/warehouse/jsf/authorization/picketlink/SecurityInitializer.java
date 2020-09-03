package pl.warehouse.jsf.authorization.picketlink;

import static org.picketlink.idm.model.basic.BasicModel.addToGroup;
import static org.picketlink.idm.model.basic.BasicModel.grantGroupRole;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

/**
 * This startup bean creates a number of default users, groups and roles when the application is
 * started.
 * 
 * @author Shane Bryzak
 */
@Singleton
@Startup
public class SecurityInitializer {

  @Inject
  private IdentityManager identityManager;

  @Inject
  private RelationshipManager relationshipManager;

  //@PostConstruct only on first run
  public void create() {
    // Create user Krzysiek
	  final User krolka = new User("asd");
	    krolka.setEmail("asd@op.pl");
	    krolka.setFirstName("asd");
	    krolka.setLastName("asd");

    identityManager.add(krolka);
    identityManager.updateCredential(krolka, new Password("qwe123"));

    // Create roles "admin" and "serviceman" and "customer"
    final Role admin = new Role("admin"); 
    identityManager.add(admin);
    final Role serviceman = new Role("serviceman");
    identityManager.add(serviceman);
    final Role customer = new Role("customer");
    identityManager.add(customer);

    // Create group "users"
    final Group users = new Group("users");
    identityManager.add(users);

    // Make krolka a member of the "users" group
    addToGroup(relationshipManager, krolka, users);

    // Make krolka a "admin" of the "users", "admin", "customer" group
    grantGroupRole(relationshipManager, krolka, admin, users);
    grantGroupRole(relationshipManager, krolka, serviceman, users);
    grantGroupRole(relationshipManager, krolka, customer, users);
  }
}