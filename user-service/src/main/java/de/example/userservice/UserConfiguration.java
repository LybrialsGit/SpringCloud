package de.example.userservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("query-parameters")
@Getter
@Setter
public class UserConfiguration {
    private String orderBy;
}
