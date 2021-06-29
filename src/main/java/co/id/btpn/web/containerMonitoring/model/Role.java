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
public class Role {
	@Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
	@Column(name="role_id")
	private Long id;
	@Column(name="role")
	private String role;
	

	
	
}
