package co.id.btpn.web.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;

import nz.net.ultraq.thymeleaf.LayoutDialect;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Ferry Fadly
 */
@SpringBootApplication
//@EnableJpaRepositories("co.id.btpn.web.containerMonitoring.repository")
public class ContainerMonitoringApplication {

	private static final Logger logger = LoggerFactory.getLogger(ContainerMonitoringApplication.class);


	
	public static void main(String[] args) {

		System.setProperty("kubernetes.trust.certificates", "true");

		SpringApplication.run(ContainerMonitoringApplication.class, args);

	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
		
	}

	// @Bean
	// RestOperations restTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
	// 	return restTemplateBuilder.basicAuthentication("username", "password").build();
	// }

}
