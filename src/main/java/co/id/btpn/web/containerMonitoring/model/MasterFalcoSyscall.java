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
public class MasterFalcoSyscall {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@GenericGenerator(name = "idgen", strategy="increment")
	@Column(name="falco_syscall_id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="value",columnDefinition="TEXT")
	private String value;
	@Column(name="fieldclass",columnDefinition="TEXT")
	private String fieldclass;
	@Column(name="description",columnDefinition="TEXT")
	private String description;
	@Column(name="remark",columnDefinition="TEXT")
	private String remark;
	@Column(name="source")
	private String source;

	
	
}
