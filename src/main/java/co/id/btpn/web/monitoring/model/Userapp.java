package co.id.btpn.web.monitoring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Userapp  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@GenericGenerator(name = "idgen", strategy="increment")
	@Column(name = "user_id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "active")
	private int active;

	
	@Column(name="CREATED_BY", nullable = true)
	private String createdBy;
	@Column(name="CREATED_DATE", nullable = true)
	private java.util.Date createdDate;
	@Column(name="MODIFIED_BY", nullable = true)
	private String modifiedBy;
	@Column(name="MODIFIED_DATE", nullable = true)
	private Date modifiedDate;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Role roleId;
}
