package expertshop.configs;
import expertshop.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
//@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
        //.passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/user/cabinet").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/user/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/")
                .and()
                    .csrf().disable();
    }
    
    /*@Bean
    public PrincipalExtractor principalExtractor(UserRepo userRepo) {
        return map -> {
            String userID = map.get("sub").toString();

            User user = userRepo.findById(userID).orElseGet(() -> {
                User newUser = new User();

                newUser.setUserID(userID);
                newUser.setFullName(map.get("fullName").toString());
                newUser.setEmail(map.get("email").toString());
                newUser.setUserPic(map.get("picture").toString());

                return newUser;
            });

            user.setRegistrationDate(LocalDateTime.now());

            return userRepo.save(user);
        };
    }*/
}