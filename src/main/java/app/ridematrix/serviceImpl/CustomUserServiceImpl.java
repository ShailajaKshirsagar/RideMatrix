package app.ridematrix.serviceImpl;

import app.ridematrix.entity.CustomUser;
import app.ridematrix.repository.UserRepo;
import app.ridematrix.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements CustomUserService, UserDetailsService
{
    @Autowired
    private UserRepo userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public String saveUser(CustomUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User saved SucessFully with user name " + user.getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUser> userOptional = userRepository.findByUsername(username);
        return userOptional.map(user -> User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(CustomUser.ROLES::name)
                        .toArray(String[]::new))
                .build()
        ).orElseThrow(() -> new UsernameNotFoundException("User not found with given username " + username));
    }
}
