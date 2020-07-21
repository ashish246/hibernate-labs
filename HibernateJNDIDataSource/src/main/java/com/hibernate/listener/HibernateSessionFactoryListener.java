package com.hibernate.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

/**
 * Since we have to initialize Hibernate SessionFactory because we can use it in
 * the application and also when web application is destroyed, we need to
 * destroy SessionFactory. So the best place to do this in a
 * ServletContextListener implementation.
 * 
 * @author Administrator
 *
 */
@WebListener
public class HibernateSessionFactoryListener implements ServletContextListener {

	public final Logger logger = Logger
			.getLogger(HibernateSessionFactoryListener.class);

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		SessionFactory sessionFactory = (SessionFactory) servletContextEvent
				.getServletContext().getAttribute("SessionFactory");
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			logger.info("Closing sessionFactory");
			sessionFactory.close();
		}
		logger.info("Released Hibernate sessionFactory resource");
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		logger.info("Hibernate Configuration created successfully");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		logger.info("ServiceRegistry created successfully");
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		logger.info("SessionFactory created successfully");

		servletContextEvent.getServletContext().setAttribute("SessionFactory",
				sessionFactory);
		logger.info("Hibernate SessionFactory Configured successfully");
	}

}