package security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import security.model.AuthorityEntity;
import security.model.UserEntity;
import security.repository.UserEntityRepository;
import java.util.List;

@Component
public class SecurityApplicationInit implements CommandLineRunner {

    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityApplicationInit(UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword(this.passwordEncoder.encode("user"));
        user.setEnabled(true);

        AuthorityEntity authority = new AuthorityEntity();
        authority.setName("ROLE_USER");
        authority.setUser(user);
        user.setAuthorities(List.of(authority));

        this.userRepository.saveAndFlush(user);

        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("admin"));
        admin.setEnabled(true);

        AuthorityEntity adminUserAuthority = new AuthorityEntity();
        adminUserAuthority.setName("ROLE_USER");
        adminUserAuthority.setUser(admin);

        AuthorityEntity adminAuthority = new AuthorityEntity();
        adminAuthority.setName("ROLE_ADMIN");
        adminAuthority.setUser(admin);
        admin.setAuthorities(List.of(adminAuthority, adminUserAuthority));

        this.userRepository.saveAndFlush(admin);
    }
}
