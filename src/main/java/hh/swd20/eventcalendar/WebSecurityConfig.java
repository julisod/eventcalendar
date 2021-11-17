package hh.swd20.eventcalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.swd20.eventcalendar.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private UserDetailServiceImpl userDetailsService;	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/static/**","/h2-console/**", "/api/**", "/events/**", "/leaders/**").permitAll() // Enable when logged out
        .and()
        .csrf().ignoringAntMatchers("/h2-console/**","/events/**")
        .and()
        .headers().frameOptions().sameOrigin()
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
      .formLogin()
          .defaultSuccessUrl("/eventlist", true)
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
/* näitä ei tarvita toisessa mallissa 
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList();

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User
        		.withUsername("user")
        		.password(passwordEncoder.encode("user"))
        		.roles("USER")
        		.build();

        users.add(user);

        user = User
        		.withUsername("admin")
        		.password(passwordEncoder.encode("admin"))
        		.roles("USER", "ADMIN")
        		.build();

    	users.add(user);

        return new InMemoryUserDetailsManager(users);
    }*/

}
