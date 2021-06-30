package co.id.btpn.web.containerMonitoring.model;


import java.util.Date;

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
public class UserLog {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userlog_id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "activity")
	private String activity;
	@Column(name="logdate")
	private Date logDate;
}
