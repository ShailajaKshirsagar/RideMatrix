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

@Service
public class CustomUserServiceImpl implements CustomUserService, UserDetailsService
{
    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String saveUser(CustomUser user) {
        //here we need to encrypt the password
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User saved SucessFully with user name " + user.getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser user = userRepo.findByUsername(username);

        if(user!=null){

           return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().stream()
                            .map(CustomUser.ROLES::name)
                            .toArray(String[]::new))
                    .build();

        }
       throw new RuntimeException("User not found with given username");
    }
}
