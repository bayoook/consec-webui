package co.id.btpn.web.monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

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
public class MasterFalcoMacro {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@GenericGenerator(name = "idgen", strategy="increment")
	@Column(name="falco_macro_id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="condition",columnDefinition="TEXT")
	private String condition;
	@Column(name="source")
	private String source;

	
	
}
