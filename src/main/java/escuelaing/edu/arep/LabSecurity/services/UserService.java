package escuelaing.edu.arep.LabSecurity.services;

import escuelaing.edu.arep.LabSecurity.entities.User;
import escuelaing.edu.arep.LabSecurity.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) throws IOException {
        return userRepository.save(user);
    }

    public User getUser(String name) throws IOException {
        return userRepository.getUser(name);
    }

    public List<User> allUsers() throws IOException {
        return userRepository.allUsers();
    }
}
