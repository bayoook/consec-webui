package co.id.btpn.web.containerMonitoring.model;

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
public class RuleFalco {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@GenericGenerator(name = "idgen", strategy="increment")
	@Column(name="rule_id")
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="description",columnDefinition="TEXT")
	private String description;
	

	@Column(name="condition",columnDefinition="TEXT")
	private String condition;
	

	@Column(name="output",columnDefinition="TEXT")
	private String output;
	

	@Column(name="tags")
	private String tags;

	@Column(name="is_default")
	private boolean isDefault;
	
}
