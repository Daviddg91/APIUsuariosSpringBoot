package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.WebContext;

import entidades.Role;
import entidades.User;
import io.micrometer.core.ipc.http.HttpSender.Request;
import repositories.RolesRepository;
import repositories.UserRepository;
import dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository roleRepository;
    @Autowired 
    RolesService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
   
    public User save(UserRegistrationDto registration){
    	
    	Optional<Role> roleCanFound = roleService.returnRepeatRole("ROLE_USER");
        Collection<Role> roles = new ArrayList();
        
		if(roleCanFound.isPresent()) {
			Role roleFound =  roleCanFound.get();
			  roles.add(roleFound);
        	
        }else {
        	Role role = new Role("ROLE_USER");

            roles.add(role);
        	
           
        	
        }
 
         User user = new User();
       
       
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(roles);
      //  user.setAvatar(registration.getAvatar());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
 
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
 
    
    
}
