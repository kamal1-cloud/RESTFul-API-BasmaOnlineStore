package ma.youcode.store.Services;

import ma.youcode.store.Config.MyUserDetails;
import ma.youcode.store.Modeles.Users;
import ma.youcode.store.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersServices implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Users user = userRepository.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }


    public List<Users> listAll() {
        return userRepository.findAll();
    }

    public Users save(Users user){
        userRepository.save(user);
        return user;
    }

    public Users get(Long id){
        return userRepository.findById(id).get();
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
