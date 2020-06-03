package fr.formation.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.people.entities.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
	// Derived queries, requêtes dérivées
	// void deleteById(Long value);
	// delete from roles where id = value

	// Role findById(Long value);
	// select * from roles where id = value

	// recupere le role par défaut
	// select * from roles where default_role = true;
	// Role findByCode(String code);
	// Role findByDefaultRole(boolean value); // false
	// List<Role> findByDefaultRoleFalse();

	Role findByDefaultRoleTrue();
	
	// Equivalent:
	// Role findByDefaultRole(boolean value);

}
