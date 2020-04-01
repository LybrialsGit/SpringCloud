package de.example.usertransfomrationservice.client;

import de.example.usertransfomrationservice.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "zuul-gateway")
@RibbonClient(name = "user-service")
public interface UserServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "user-service/users")
    List<User> getUsers();
}
