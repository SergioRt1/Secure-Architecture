package escuelaing.edu.arep.LabSecurity.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import escuelaing.edu.arep.LabSecurity.entities.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserRepository {
    private final File usersFile;
    private final ObjectMapper objectMapper;

    public UserRepository(ObjectMapper objectMapper) throws IOException {
        this.objectMapper = objectMapper;
        this.usersFile = new ClassPathResource("data/users.dat").getFile();
    }

    public User save(User user) throws IOException {
        String userAsJson = objectMapper.writeValueAsString(user) + '\n';
        Files.write(usersFile.toPath(), userAsJson.getBytes(), StandardOpenOption.APPEND);

        return user;
    }


    public User getUser(String name) throws IOException {
        List<User> users = allUsers();
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }

        throw new NoSuchElementException("Not found user " + name);
    }

    public List<User> allUsers() throws IOException {
        List<User> users = new ArrayList<>();
        Files.lines(usersFile.toPath()).forEach(line -> {
            try {
                User user = objectMapper.readValue(line, User.class);
                users.add(user);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return users;
    }
}
