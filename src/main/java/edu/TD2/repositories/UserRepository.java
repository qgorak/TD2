package edu.TD2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.TD2.models.Organization;
import edu.TD2.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	

}
