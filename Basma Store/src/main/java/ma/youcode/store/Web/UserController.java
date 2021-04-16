package ma.youcode.store.Web;

import ma.youcode.store.Modeles.Users;
import ma.youcode.store.Repositories.UsersRepository;
import ma.youcode.store.Services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {
    @Autowired
    private UsersServices usersServices;

    @GetMapping("/")
    public String hello(){
        return "HELLO KAMAL";
    }

    @GetMapping("/all")
    public List<Users> findAll() {
        return usersServices.listAll();
    }

    @PostMapping("/register")
    public Users newUser(@RequestBody Users user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return usersServices.save(user);
    }

//    Find user by id

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> get(@PathVariable Long id) {
        try {
            Users user = usersServices.get(id);
            return new ResponseEntity<Users>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> update(@RequestBody Users user, @PathVariable Long id) {
        try {
            Users existUser = usersServices.get(id);
            usersServices.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
