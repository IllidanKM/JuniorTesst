package net.tryhard.juniortesst.controller;

import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.dto.UserDTOCreate;
import net.tryhard.juniortesst.dto.UserDTOUpdate;
import net.tryhard.juniortesst.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserDTO> findAll() {
        return userService.findAll();

    }

    @GetMapping("/users-filtered")
    public List<UserDTO> getUsersFiltered(@RequestParam(name = "firstName", required = false) String firstName,
                                          @RequestParam(name = "lastName", required = false) String lastName,
                                          @RequestParam(name = "middleName", required = false) String middleName,
                                          @RequestParam(name = "dateOfBirth", required = false) String dateOfBirth) {
        return userService.searchUsers(firstName, lastName, middleName, dateOfBirth);
    }

    @PostMapping("/user-create")
    public UserDTO createUser(@RequestBody UserDTOCreate userDTOCreate) {

        return userService.saveUser(userDTOCreate);
    }

    @DeleteMapping("user-delete")
    public Long deleteUser(@RequestParam(name = "id") Long id) {
        userService.deleteById(id);
        return id;
    }

    @PutMapping("/user-update")
    public UserDTO updateUser(@RequestParam Long id,
                              @RequestBody UserDTOUpdate userDTOUpdate) {
        return userService.updateUser(id, userDTOUpdate);
    }


}
