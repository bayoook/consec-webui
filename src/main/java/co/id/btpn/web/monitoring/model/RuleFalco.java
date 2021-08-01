package co.id.btpn.web.monitoring.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private MasterFalcoCondition masterFalcoCondition;
	
	@Column(name="output",columnDefinition="TEXT")
	private String output;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private MasterFalcoPriority masterFalcoPriority;

	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "master_rule_tags_falco", joinColumns = @JoinColumn(name = "rule_id"), inverseJoinColumns = @JoinColumn(name = "tags_id"))
	private Set<MasterFalcoTags> masterFalcoTags;
	
	@Column(name="is_default")
	private boolean isDefault;
	
}
