package  services;

import org.springframework.security.core.userdetails.UserDetailsService;

import entidades.User;
import  dto.UserRegistrationDto;
public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}