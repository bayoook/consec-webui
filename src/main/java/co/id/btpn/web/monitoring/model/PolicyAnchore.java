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
public class PolicyAnchore {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@GenericGenerator(name = "idgen", strategy="increment")
	@Column(name="policy_id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="desc")
	private String description;
	@Column(name="comment")
	private String comment;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "policy_rule_anchore", joinColumns = @JoinColumn(name = "policy_id"), inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<RuleAnchore> ruleAnchore;
	
}
