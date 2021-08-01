package co.id.btpn.web.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.context.annotation.Bean;
import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 *
 * @author Ferry Fadly
 */
@SpringBootApplication
//@EnableJpaRepositories("co.id.btpn.web.containerMonitoring.repository")
public class ContainerMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContainerMonitoringApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
		
	}

}
