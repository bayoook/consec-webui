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
public class RuleAnchore {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@GenericGenerator(name = "idgen", strategy="increment")
	@Column(name="rule_id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="action")
	private String action;
	@Column(name="gate")
	private String gate;
	@Column(name="trigger")
	private String trigger;

	@ManyToMany(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(name = "params_rule_anchore", joinColumns = @JoinColumn(name = "policy_id"), inverseJoinColumns = @JoinColumn(name = "rule_id"))
	private Set<RuleParamsAnchore> ruleParamsAnchore;
}
