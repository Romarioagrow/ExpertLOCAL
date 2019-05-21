package expertshop.configs;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/user").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                    .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
