package security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import security.user.DemoUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final DemoUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, DemoUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

       // auth
       //         .jdbcAuthentication()
       //         .withDefaultSchema()
       //         .dataSource(dataSource)
       //         .passwordEncoder(passwordEncoder)
       //         .withUser("user").password(passwordEncoder.encode("user")).roles("USER")
       //         .and()
       //         .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN", "USER");
    }
}
