package co.id.btpn.web.monitoring.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


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
public class CustomRuleActionFalco {


	@SerializedName("action_name")
	@Expose
	public String actionName;
	@SerializedName("id")
	@Expose
	public Integer id;

	
}
