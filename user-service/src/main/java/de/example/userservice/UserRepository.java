package de.example.userservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByLastName();

    List<User> findAllByOrderByFirstName();

    List<User> findAllByOrderByAge();
}
