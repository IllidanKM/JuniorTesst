package net.tryhard.juniortesst.controller;

import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.dto.UserDTOCreate;
import net.tryhard.juniortesst.dto.UserDTOFIltered;
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
    public List<UserDTO> findAll( @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.findAll(pageNumber,pageSize);

    }

    @GetMapping("/users-filtered")
    public List<UserDTO> getUsersFiltered(@ModelAttribute UserDTOFIltered userDTOFIltered,
                                          @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.searchUsers(userDTOFIltered, pageNumber, pageSize);
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
