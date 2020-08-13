package pl.warehouse.jsf.authorization.picketlink;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class HttpSecurityConfiguration {

    public void onInit(@Observes SecurityConfigurationEvent event) {
        SecurityConfigurationBuilder builder = event.getBuilder();

        builder
            .http()
            .forPath("/javax.faces.resource/*")
            	.unprotected()
                	.allPaths()
                		.authenticateWith()
                			.form()
                				.authenticationUri("/faces/pages/login.xhtml")
                				.loginPage("/faces/pages/login.xhtml")
                				.restoreOriginalRequest();
    }
}