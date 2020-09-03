package pl.warehouse.jsf.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

import lombok.Getter;
import lombok.Setter;

@IdentityManaged(org.picketlink.idm.model.basic.User.class)
@Entity
@Table(name = "AccountTypeEntity")
public class UserEntity extends IdentityTypeEntity {

    private static final long serialVersionUID = -7779146512584924566L;

    @Getter
    @Setter
    @Column(name = "loginName")
    @NotNull
    @AttributeValue
    private String loginName;

    @Getter
    @Setter
    @Column(name = "firstName")
    @NotNull
    @AttributeValue
    private String firstName;

    @Getter
    @Setter
    @Column(name = "lastName")
    @NotNull
    @AttributeValue
    private String lastName;

    @Getter
    @Setter
    @Column(name = "email")
    @NotNull
    @AttributeValue
    private String email;

    public UserEntity() {
	/** Do nothing */
    }

}

