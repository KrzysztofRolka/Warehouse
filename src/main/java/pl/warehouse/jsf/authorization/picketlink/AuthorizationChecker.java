package pl.warehouse.jsf.authorization.picketlink;

import static org.picketlink.idm.model.basic.BasicModel.getGroup;
import static org.picketlink.idm.model.basic.BasicModel.getRole;
import static org.picketlink.idm.model.basic.BasicModel.hasRole;
import javax.inject.Inject;
import javax.inject.Named;
import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;

/**
 * This is a utility bean that may be used by the view layer to determine whether the current user
 * has specific privileges.
 * 
 * @author Shane Bryzak
 *
 */
@Named
public class AuthorizationChecker {

  @Inject
  private Identity identity;

  @Inject
  private IdentityManager identityManager;

  @Inject
  private RelationshipManager relationshipManager;

  public boolean hasApplicationRole(String roleName) {
    final Role role = getRole(this.identityManager, roleName);
    return hasRole(this.relationshipManager, this.identity.getAccount(), role);
  }

  public boolean hasNotApplicationRole(String roleName) {
	    final Role role = getRole(this.identityManager, roleName);
	    return !hasRole(this.relationshipManager, this.identity.getAccount(), role);
	  }
  
  public boolean isMember(String groupName) {
    final Group group = getGroup(this.identityManager, groupName);
    return BasicModel.isMember(this.relationshipManager, this.identity.getAccount(), group);
  }

  public boolean hasGroupRole(String roleName, String groupName) {
    final Group group = getGroup(this.identityManager, groupName);
    final Role role = getRole(this.identityManager, roleName);
    return BasicModel.hasGroupRole(this.relationshipManager, this.identity.getAccount(), role, group);
  }
}
