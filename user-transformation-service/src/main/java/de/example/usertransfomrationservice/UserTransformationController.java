package de.example.usertransfomrationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.example.usertransfomrationservice.client.UserServiceClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class UserTransformationController {

    private final UserServiceClient client;

    @Autowired
    public UserTransformationController(final UserServiceClient client) {
        this.client = client;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUsers() throws JsonProcessingException {
        final List<User> users = client.getUsers();

        log.info("Users: {}", users);

        return new ObjectMapper().writeValueAsString(users);
    }
}
