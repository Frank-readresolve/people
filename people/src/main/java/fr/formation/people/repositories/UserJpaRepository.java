package fr.formation.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.people.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
