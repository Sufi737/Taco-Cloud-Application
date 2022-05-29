package com.springinaction.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springinaction.tacocloud.jpa.entities.User;
import com.springinaction.tacocloud.jpa.repositories.UserRepository;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//below was used for in-memory storage of users
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//		List<UserDetails> usersList = new ArrayList<>();
//		usersList.add(new User(
//			"buzz", encoder.encode("password"),
//			Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")))
//		);
//		usersList.add(new User(
//			"woody", encoder.encode("password"),
//			Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")))
//		);
//		return new InMemoryUserDetailsManager(usersList);
//	}
	
	//below bean is essential for login to work
	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			User user = userRepo.findByUsername(username);
			if (user != null) return user;
			throw new UsernameNotFoundException("User '" + username + "' not found");
		};
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/design", "/orders/**").hasRole("USER")
				.antMatchers("/", "/**").permitAll()
			
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/design")
					
			.and()
				.logout()
				
			
			.and()
			.build();
	}
}
