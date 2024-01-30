package net.tryhard.juniortesst.controller;

import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.dto.UserDTOCreate;
import net.tryhard.juniortesst.dto.UserDTOUpdate;
import net.tryhard.juniortesst.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users//{pageNumber}/{pageSize}")
    public List<UserDTO> findAll(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize) {
        return userService.findAll(pageNumber, pageSize);

    }

    @GetMapping("/users-filtered")
    public List<UserDTO> getUsersFiltered(@RequestParam(name = "id", required = false) Long id,
                                  @RequestParam(name = "firstName", required = false) String firstName,
                                  @RequestParam(name = "lastName", required = false) String lastName,
                                  @RequestParam(name = "middleName", required = false) String middleName,
                                  @RequestParam(name = "dateOfBirth", required = false) LocalDate dateOfBirth) {
        return userService.findAllFiltered(firstName,lastName,middleName,dateOfBirth);


    }


    @PostMapping("/user-create")
    public UserDTO createUser(@RequestBody UserDTOCreate userDTOCreate) {
        return userService.saveUser(userDTOCreate);
    }


    @DeleteMapping("user-delete/{id}")
    public Long deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return id;
    }

    @PutMapping("/user-update")
    public UserDTO updateUser(@RequestParam Long id,
                              @RequestBody UserDTOUpdate userDTOUpdate) {
        return userService.updateUser(id, userDTOUpdate);

    }


}
