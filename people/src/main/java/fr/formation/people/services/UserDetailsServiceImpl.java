package fr.formation.people.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.people.entities.User;
import fr.formation.people.repositories.UserJpaRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserJpaRepository repo;

	public UserDetailsServiceImpl(UserJpaRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("no user found with username: " + username);
		}
		return toSpringUser(user);
	}

	private static org.springframework.security.core.userdetails.User toSpringUser(User user) {
		String password = user.getPassword();
		boolean enabled = user.isEnabled();
		Set<GrantedAuthority> authorities = new HashSet<>();
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getCode());
		authorities.add(authority);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), password, enabled, true, true, true,
				authorities);
	}

}
