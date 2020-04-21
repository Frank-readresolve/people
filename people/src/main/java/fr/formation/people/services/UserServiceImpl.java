package fr.formation.people.services;

import org.springframework.stereotype.Service;

import fr.formation.people.dtos.UserCreateDto;
import fr.formation.people.entities.Role;
import fr.formation.people.entities.User;
import fr.formation.people.repositories.RoleJpaRepository;
import fr.formation.people.repositories.UserJpaRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserJpaRepository userRepo;

	private final RoleJpaRepository roleRepo;

	public UserServiceImpl(UserJpaRepository userRepo, 
			RoleJpaRepository roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public void create(UserCreateDto dto) {
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		Role role = roleRepo.findByDefaultRoleTrue();
		user.setRole(role);
		userRepo.save(user);
	}

}
