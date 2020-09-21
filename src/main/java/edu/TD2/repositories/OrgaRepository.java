package edu.TD2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.TD2.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {

}
