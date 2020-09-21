package edu.TD2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.TD2.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
	
    List<Organization> findByDomain(String domain);
    List<Organization> findAll();
    List<Organization> deleteById(int id);

    
	public Optional<Organization> findByName(String name);
	public Organization findById(int id);
	@Query("update Organization c set c.name = :name WHERE c.id = :id")
	     void setOrgaName(@Param("id") int id, @Param("name") String name);

	  }


