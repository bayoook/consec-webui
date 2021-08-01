package co.id.btpn.web.monitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.btpn.web.monitoring.model.MasterFalcoTags;

@Repository("masterFalcoTagsRepository")
public interface MasterFalcoTagsRepository extends JpaRepository<MasterFalcoTags, Long>  {
    
}
