package com.slksoft.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@PropertySource({ "classpath:jdbc-info.properties" })
@Configuration
@ComponentScan(basePackages = { "com.slksoft.service","com.slksoft.web" })
@MapperScan({"com.slksoft.dao"})
public class AppConfig implements WebMvcConfigurer{

	@Value("${jdbc.driver}")
	private String driver = "com.mysql.cj.jdbc.Driver";
	@Value("${jdbc.url}")
	private String url = "jdbc:mysql://localhost/slktraining"  ;
	@Value("${jdbc.username}")
	private String username = "root";
	@Value("${jdbc.password}")
	private String password = "root";

	//this is to inform DispatcherServlet to let go of requests (css,js,images)
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		return bds;

	}

	//USing this bean, mybatis-spring engine creates instances for all mapper instances 
	//found in @MapperScan packages and loads in spring container
	@Bean
	public SqlSessionFactory factory(DataSource ds) throws Exception {

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean(); //mybatis-spring
		bean.setDataSource(ds);
		return bean.getObject();
	}
	
	//override the default behavior of InternalResourceViewResolver
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
