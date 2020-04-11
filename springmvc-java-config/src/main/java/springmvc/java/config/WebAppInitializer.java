package springmvc.java.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		/*
		<servlet>
		   <servlet-name>dispatcher</servlet-name>
		   <servlet-class>
		     org.springframework.web.servlet.DispatcherServlet
		   </servlet-class>
		   <init-param>
		     <param-name>contextConfigLocation</param-name>
		     <param-value>/WEB-INF/spring/dispatcher-config.xml</param-value>
		   </init-param>
		   <load-on-startup>1</load-on-startup>
		 </servlet>

		 <servlet-mapping>
		   <servlet-name>dispatcher</servlet-name>
		   <url-pattern>/</url-pattern>
		 </servlet-mapping>
		*/
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebConfig.class);
		
		//manage the lifecycle of rootcontext
		container.addListener(new ContextLoaderListener(rootContext));
		
		DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(rootContext);
		
		ServletRegistration.Dynamic registration = container.addServlet("dispatcherServlet", dispatcherServlet);
		
		container.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
		
		registration.setLoadOnStartup(1);
		registration.addMapping("/");
		
	}

}
