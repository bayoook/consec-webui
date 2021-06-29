package co.id.btpn.web.containerMonitoring.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuditListener {

	@Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
	@Column(name="audit_id")
	private Long id;

    @Column(name = "operation")
    private String operation;
     
    @Column(name = "timestamp")
    private long timestamp;
     

    @PrePersist
    public void onPrePersist() {
        audit("INSERT");
    }
     
    @PreUpdate
    public void onPreUpdate() {
        audit("UPDATE");
    }
     
    @PreRemove
    public void onPreRemove() {
        audit("DELETE");
    }

    private void audit(String operation) {
        setOperation(operation);
        setTimestamp((new Date()).getTime());
    }
    
    // @PrePersist
    // @PreUpdate
    // @PreRemove
    // private void beforeAnyOperation(Object object) {


    //  }
    
}