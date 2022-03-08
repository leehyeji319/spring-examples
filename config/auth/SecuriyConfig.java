package recorder.backend.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.RequiredArgsConstructor;
import recorder.backend.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecuriyConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.headers().frameOptions().disable().and()
			.authorizeHttpRequests()
			.antMatchers("/", "/css/**", "/images/**",
				"js/**", "/h2-console/**").permitAll()
			.antMatchers("/api/v1/**").hasRole(Role.USER.name())
			.anyRequest().authenticated()
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.oauth2Login()
			.userInfoEndpoint()
			.userService(customOAuth2UserService);
	}
}
