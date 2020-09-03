package pl.warehouse.jsf.services;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.Account;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IdentityService {
	
	@Inject
    private IdentityManager identityManager;
	
	
	
	public void changeUserPassword(final Account user, final Password password) {
		identityManager.updateCredential(user, password);
	    }
}
