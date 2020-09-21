package edu.TD2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.TD2.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
	
    List<Organization> findByDomain(String domain);
    List<Organization> findAll();
    
	public Optional<Organization> findByName(String name);
	

}
