package de.example.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class UserController {

    private final UserConfiguration configuration;
    private final UserRepository repository;

    @Autowired
    public UserController(final UserConfiguration configuration,
                          final UserRepository repository) {
        this.configuration = configuration;
        this.repository = repository;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_XML_VALUE)
    public String getUsers() throws JsonProcessingException {
        final List<User> users;

        if (configuration.getOrderBy().equals(UserOrderBy.LAST_NAME.getValue())) {
            users = repository.findAllByOrderByLastName();
        } else if (configuration.getOrderBy().equals(UserOrderBy.FIRST_NAME.getValue())) {
            users = repository.findAllByOrderByFirstName();
        } else if (configuration.getOrderBy().equals(UserOrderBy.AGE.getValue())) {
            users = repository.findAllByOrderByAge();
        } else {
            users = repository.findAll();
        }

        log.info("Order by: {}, Users: {}", configuration.getOrderBy(), users);

        return new XmlMapper().writeValueAsString(users);
    }
}
