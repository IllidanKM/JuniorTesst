package net.tryhard.juniortesst.service;

import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.dto.UserDTOCreate;
import net.tryhard.juniortesst.dto.UserDTOUpdate;

import net.tryhard.juniortesst.mapper.UserMapper;
//import net.tryhard.juniortesst.mapper.UserMapperImpl;
import net.tryhard.juniortesst.model.User;
import net.tryhard.juniortesst.repository.UserRepository;
import net.tryhard.juniortesst.repository.custom.UserRepositoryCustom;
import net.tryhard.juniortesst.repository.custom.implementation.UserRepositoryCustomImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    public  List<UserDTO> findAllFiltered( String firstName, String lastName, String middleName, LocalDate dateOfBirth) {

        return userRepository.filterTable(firstName,lastName,middleName,dateOfBirth);
        }

    public List<UserDTO> findAll() {


        return userRepository.findAll()
                .stream()
                .map(userMapper::mapUserDTO)
                .toList();
    }

    public UserDTO saveUser(UserDTOCreate userDTOCreate) {
        User user = userRepository.save(userMapper.mapUser(userDTOCreate));
        return userMapper.mapUserDTO(user);

    }

    public UserDTO updateUser(Long id, UserDTOUpdate userDTOUpdate){
        User user = userRepository.findById(id).orElse(null);
        user.setFirstName(userDTOUpdate.getFirstName());
        user.setLastName(userDTOUpdate.getLastName());
        user.setMiddleName(userDTOUpdate.getMiddleName());
        user.setDateOfBirth(userDTOUpdate.getDateOfBirth());
        return userMapper.mapUserDTO(userRepository.save(user));
    }

    public void deleteById(Long id){
        User user = userRepository.findById(id).orElse(null);

        userRepository.deleteById(id);

    }
}
