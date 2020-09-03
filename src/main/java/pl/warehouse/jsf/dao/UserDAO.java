package pl.warehouse.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.basic.User;
import org.picketlink.idm.query.Condition;
import org.picketlink.idm.query.IdentityQueryBuilder;

import pl.warehouse.jsf.entities.UserEntity;


@Stateless
public class UserDAO {

	@Inject
    private IdentityManager identityManager;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Query all existing {@link User} from PicketLink tables.
     * 
     * @return {@link List} of {@link User}
     */
    public List<User> getUsers() {
	final IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
	final Condition condition = queryBuilder.equal(User.ENABLED, true);
	return queryBuilder.createIdentityQuery(User.class).where(condition).getResultList();
    }

    public UserEntity getById(String id) {
    	return entityManager.find(UserEntity.class, id);
    }
    
	public void updateUser(UserEntity user) {
		entityManager.merge(user);
		entityManager.flush();
	}

}
