package pl.warehouse.jsf.services;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;
import static org.picketlink.idm.model.basic.BasicModel.addToGroup;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UsersService {
	
	@Inject
    private IdentityManager identityManager;
	
	@Inject
	private RelationshipManager relationshipManager;
	
	
	public void changeUserPassword(final Account user, final Password password) {
		identityManager.updateCredential(user, password);
	    }
	
	public void updateUser(User user) {
		identityManager.update(user);
	}
	
	public void deleteUser(User user) {
		identityManager.remove(user);
	}

	public void saveNewUser(User user, final String password, final boolean isAdmin,final boolean isServiceman,final boolean isCustomer) {
		
    	identityManager.add(user);
		
	    identityManager.updateCredential(user, new Password(password));
	    
	    
	    Group group = BasicModel.getGroup(identityManager, "users");
	    addToGroup(relationshipManager, user, group);
	    
	    if(isAdmin) {
	    	Role admin = BasicModel.getRole(identityManager, "admin");
	    	BasicModel.grantRole(relationshipManager, user, admin);
	    }
	    
	    if(isServiceman) {
	    	Role serviceman = BasicModel.getRole(identityManager, "serviceman");
	    	BasicModel.grantRole(relationshipManager, user, serviceman);
	    }
	    
	    if(isCustomer) {
	    	Role customer = BasicModel.getRole(identityManager, "customer");
	    	BasicModel.grantRole(relationshipManager, user, customer);
	    }
		
	}
	
	public void updateUser(User user, final boolean isAdmin,final boolean isServiceman,final boolean isCustomer) {
		
    	identityManager.update(user);
		
	    
	    Role admin = BasicModel.getRole(identityManager, "admin");

	    if(isAdmin == true && BasicModel.hasRole(relationshipManager, user, admin)==false) {
	    	BasicModel.grantRole(relationshipManager, user, admin);
	    }
	    if(isAdmin == false && BasicModel.hasRole(relationshipManager, user, admin)==true) {
	    	BasicModel.revokeRole(relationshipManager, user, admin);
	    }
	    
	    Role serviceman = BasicModel.getRole(identityManager, "serviceman");
	    
	    if(isServiceman == true && BasicModel.hasRole(relationshipManager, user, serviceman)==false) {
	    	BasicModel.grantRole(relationshipManager, user, serviceman);
	    }
	    if(isServiceman == false && BasicModel.hasRole(relationshipManager, user, serviceman)==true) {
	    	BasicModel.revokeRole(relationshipManager, user, serviceman);
	    }

	    
	    Role customer = BasicModel.getRole(identityManager, "customer");
		
	    if(isCustomer== true && BasicModel.hasRole(relationshipManager, user, customer)==false) {
	    	BasicModel.grantRole(relationshipManager, user, customer);
	    }
	    if(isCustomer == false && BasicModel.hasRole(relationshipManager, user, customer)==true) {
	    	BasicModel.revokeRole(relationshipManager, user, customer);
	    }
	}
	
	public void updateUserPassword(User user, final String password) {
		identityManager.updateCredential(user, new Password(password));
	}
}