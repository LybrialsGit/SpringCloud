package de.example.usertransfomrationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class User {
    private Long id;
    private String lastName;
    private String firstName;
    private String age;
}
