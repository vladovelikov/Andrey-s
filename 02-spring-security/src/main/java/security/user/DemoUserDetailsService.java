package security.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import security.model.AuthorityEntity;
import security.model.UserEntity;
import security.repository.UserEntityRepository;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DemoUserDetailsService implements UserDetailsService {

    private final UserEntityRepository userRepository;

    @Autowired
    public DemoUserDetailsService(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);
        return optionalUser.map(this::map).orElseThrow(() -> new UsernameNotFoundException("User" + username + "not found."));
    }

    private UserDetails map(UserEntity userEntity) {
        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(),
                userEntity.getAuthorities()
                        .stream()
                        .map(this::map)
                        .collect(Collectors.toList()));
    }

    private GrantedAuthority map(AuthorityEntity authority) {
        return new SimpleGrantedAuthority(authority.getName());
    }
}
