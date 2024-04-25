package com.c1736.bankservice.client;

import com.c1736.bankservice.client.dto.UserDTO;
import com.c1736.bankservice.client.interceptor.FeignClientInterceptor;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service", url = "http://user-service:8091/api/v1/", configuration = FeignClientInterceptor.class)
public interface IUserFeignClient {

    @GetMapping("admin/findUser/{id}")
    public UserDTO findUserById(@PathVariable Long id);

    @GetMapping("admin/findAllUser")
    public List<UserDTO> findAllUsers();

    @GetMapping("admin/updateUser/{id}")
    public UserDTO updateUserById(@PathVariable Long id, @RequestBody UserDTO user);

    @GetMapping("admin/deleteUser/{Id}")
    public void deleteUserById(@PathVariable Long Id);

    @GetMapping("client/saveUser")
    public void saveUser(@Valid @RequestBody UserDTO user);

    @GetMapping("company/getUser")
    UserDTO getUserCompany(@RequestParam String email);
    @GetMapping("client/getUser")
    UserDTO getUserClient(@RequestParam String email);
}
