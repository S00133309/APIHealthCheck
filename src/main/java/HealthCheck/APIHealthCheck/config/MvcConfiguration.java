package HealthCheck.APIHealthCheck.config;

import javax.sql.DataSource;

import HealthCheck.APIHealthCheck.dao.APIDAO;
import HealthCheck.APIHealthCheck.dao.APIDAOImpl;
import HealthCheck.APIHealthCheck.dao.PersonDAO;
import HealthCheck.APIHealthCheck.dao.PersonDAOImpl;
import HealthCheck.APIHealthCheck.dao.ResultDAO;
import HealthCheck.APIHealthCheck.dao.ResultDAOImpl;
import HealthCheck.APIHealthCheck.dao.URLDAO;
import HealthCheck.APIHealthCheck.dao.URLDAOImpl;
import HealthCheck.APIHealthCheck.service.Ping;
import HealthCheck.APIHealthCheck.service.Timing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "HealthCheck.APIHealthCheck")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:C:\\Users\\User\\Desktop\\Java\\Workspace\\APIHealthCheck\\DB\\HealthCheckDB");
		dataSource.setUsername("sa");
		dataSource.setPassword("");

		return dataSource;
	}

	@Bean
	public PersonDAO getPersonDAO() {
		return new PersonDAOImpl(getDataSource());
	}

	@Bean
	public APIDAO getApiDAO() {
		return new APIDAOImpl(getDataSource());
	}

	@Bean
	public URLDAO getUrlDAO() {
		return new URLDAOImpl(getDataSource());
	}
	
	@Bean
	public ResultDAO getResultDAO() {
		return new ResultDAOImpl(getDataSource());
	}
	
	@Bean
	public Timing getTiming() {
		return new Timing();
	}
	
	@Bean
	public Ping getPing() {
		return new Ping();
	}

}
