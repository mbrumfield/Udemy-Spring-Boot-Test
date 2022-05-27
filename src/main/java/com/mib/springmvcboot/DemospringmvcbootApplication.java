package com.mib.springmvcboot;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DemospringmvcbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemospringmvcbootApplication.class, args);
	}
	
	@Bean
	@Profile("local")
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {

			@Override
			protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
				tomcat.enableNaming();
				return super.getTomcatWebServer(tomcat);
			}

			@Override
			protected void postProcessContext(Context context) {

				ContextResource resource = new ContextResource();
				resource.setName("jdbc/MySQL");
				resource.setAuth("Container");
				resource.setType("com.mchange.v2.c3p0.ComboPooledDataSource");
				
				resource.setProperty("jdbcUrl", "jdbc:mysql://127.0.0.1:3306/userrepo");
				resource.setProperty("factory", "org.apache.naming.factory.BeanFactory");
				resource.setProperty("user", "root");
				resource.setProperty("password", "M0n3yDatabas3$");

				context.getNamingResources().addResource(resource);

			}

		};

		return tomcat;

	}


}
