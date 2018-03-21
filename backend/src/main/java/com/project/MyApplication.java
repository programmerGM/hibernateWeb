package com.project;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Class configuration Jersey.
 * 
 * @author Mauricio Generoso.
 * @since 18/03/2018.
 * @version 0.1
 */
@ApplicationPath("api")
public class MyApplication extends ResourceConfig {

    /**
     * Construct.
     */
    public MyApplication() {
	packages("com.project.endpoint");
    }
}