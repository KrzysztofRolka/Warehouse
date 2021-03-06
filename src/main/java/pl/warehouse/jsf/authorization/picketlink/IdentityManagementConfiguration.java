package pl.warehouse.jsf.authorization.picketlink;

import javax.enterprise.inject.Produces;
import org.picketlink.idm.config.IdentityConfiguration;
import org.picketlink.idm.config.IdentityConfigurationBuilder;

/**
 * This bean produces the configuration for PicketLink IDM
 * 
 * 
 * @author Shane Bryzak
 *
 */
public class IdentityManagementConfiguration {

  /**
   * This method uses the IdentityConfigurationBuilder to create an IdentityConfiguration, which
   * defines how PicketLink stores identity-related data. In this particular example, a
   * JPAIdentityStore is configured to allow the identity data to be stored in a relational database
   * using JPA.
   */
  @Produces
  IdentityConfiguration produceIdentityManagementConfiguration() {
    IdentityConfigurationBuilder builder = new IdentityConfigurationBuilder();

    builder.named("default").stores().jpa().supportAllFeatures();

    return builder.build();
  }

}
