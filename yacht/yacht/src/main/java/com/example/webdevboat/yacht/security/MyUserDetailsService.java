package com.example.webdevboat.yacht.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.webdevboat.yacht.model.User;





@Service
public class MyUserDetailsService  {

	@Autowired
	private com.example.webdevboat.yacht.repository.UserRepository userRepository;
	
}
	
/*	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findUserByEmail(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + username);
	        }
	        // Récupérer les rôles de l'utilisateur depuis la base de données
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        for (Role role : user.getRoles()) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	    }
	

    
	private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
		return roles.stream()
	            .map(role -> new SimpleGrantedAuthority(role.getName()))
	            .collect(Collectors.toList());
	}
}


/*   @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	User user = userRepository.findUserByEmail(username);
	
	boolean accountNonExpired = true; 
	boolean credentialsNonExpired = true; 
	boolean accountNonLocked = true;
	
	UserDetails userDetails = new org.springframework.security.core.userdetails.User(
			username, 
			user.getPassword(), 
			true,
			accountNonExpired, 
			credentialsNonExpired, 
			accountNonLocked, 
			getAuthorities(user.getRoles()));
	
	return userDetails;
}*/