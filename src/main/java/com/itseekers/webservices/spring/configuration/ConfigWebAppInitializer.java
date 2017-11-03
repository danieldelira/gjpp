/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.spring.configuration;

import com.itseekers.webservices.spring.hibernate.HibernateConfiguration;
import com.itseekers.webservices.spring.security.SecurityConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author cheve360
 */
public class ConfigWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // Crea el root del conexto de la aplicacion
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class, HibernateConfiguration.class, SecurityConfig.class);
        rootContext.setServletContext(container);

        // Administra el ciclo de vida  del conexto de la aplicacion
        container.addListener(new ContextLoaderListener(rootContext));

        Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(rootContext));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

}
