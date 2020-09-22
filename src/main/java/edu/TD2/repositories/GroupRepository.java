package edu.TD2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.TD2.models.Groupe;
import edu.TD2.models.Organization;
import edu.TD2.models.User;

public interface GroupRepository extends JpaRepository<Groupe, Integer> {
    List<Groupe> findAll();
    



	

}
