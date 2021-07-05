package co.id.btpn.web.containerMonitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Ferry Fadly
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConfigFalco{
	@Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
	@Column(name="config_id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="config",columnDefinition="TEXT")
	private String config;

}
