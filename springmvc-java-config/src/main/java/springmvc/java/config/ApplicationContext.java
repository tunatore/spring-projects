package springmvc.java.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springmvc.java.service.BlogPostService;
import springmvc.java.service.impl.BlogPostServiceImpl;

@EnableJpaRepositories(basePackages={"springmvc.java.dao"})
@EnableTransactionManagement
@Configuration
public class ApplicationContext {

	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource() {		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driverClass"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));		
		return dataSource;
	}

	private DatabasePopulator databasePopulator() {
		
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(false);
		databasePopulator.addScript(new ClassPathResource("test-data.sql"));
		return databasePopulator;
		
	}
	
	@Bean
	public BlogPostService blogPostService () {		
		return new BlogPostServiceImpl();		
	}
	
	
	@Bean
	@Qualifier("embedded")
	public DataSource dataSourceEmbedded(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase embeddedDatabase = builder
												.setType(EmbeddedDatabaseType.HSQL)
												.addScript("dbschema.sql")
												.addScript("test-data.sql")
												.build();
		return embeddedDatabase;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();		
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);	
		
		DatabasePopulatorUtils.execute(databasePopulator(), dataSource());
		
		return jpaTransactionManager;
		
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		
		//JPA Java Persistence API (tHE Standard Specification for accessing databases through Java Objects)
		//Hibernate Implementation of JPA is used 
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		
		//JPA vendor adapter will set SQL syntax to MYSQL
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		
		//Database tables-setGenerateDdl(true);
		//setting this will show SQLS on logs
		jpaVendorAdapter.setShowSql(true);
		
		return jpaVendorAdapter;
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
/*	     <!-- JPA Persistence Context and EntityManager configuration -->
	 	<bean id="entityManagerFactory"
	         class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean" >
	         <property name="packagesToScan" value="spring.mvc.bean" />
	         <property name="dataSource" ref="dataSource" />
	         <property name="jpaVendorAdapter">
	             <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" >
	             	<property name="generateDdl" value="true" />
	 				<property name="showSql" value="true" />				
	             </bean>
	         </property>
	         <property name="jpaProperties">
	             <props>
	                 <prop key="hibernate.dialect">org.hibernate.dialect.HSQLDialect</prop>
	                 <prop key="hibernate.show_sql">true</prop>
	                 <prop key="hibernate.format_sql">false</prop>
	                 <prop key="hibernate.hbm2ddl.auto">update</prop> 			                               
	             </props>
	         </property>
	     </bean>    
		*/
		
		//Entity Manager Factory configuration is required in order to manage entities (tables) in Spring MVC
		//Entity Manager Factory will manage the life cycle of entities in Java 
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		//Entity manager factory requires a data source in order to store/list entities
		entityManagerFactory.setDataSource(dataSource());
		
		//Java Config Dependency injection is provided here by setting JPA Vendor Adapter (Hibernate)
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
		
		//Entity Manager Factory will scan packages in order to find entities (@Entity)
		entityManagerFactory.setPackagesToScan("springmvc.java.domain");
		
		//Custom properties can be set using Properties
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		
		entityManagerFactory.setJpaProperties(jpaProperties);
		
		return entityManagerFactory;
		
	}

	
}
