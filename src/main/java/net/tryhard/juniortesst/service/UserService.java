package net.tryhard.juniortesst.service;

import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.dto.UserDTOCreate;
import net.tryhard.juniortesst.dto.UserDTOFIltered;
import net.tryhard.juniortesst.dto.UserDTOUpdate;
import net.tryhard.juniortesst.exception.NotFoundException;
import net.tryhard.juniortesst.mapper.UserMapper;
import net.tryhard.juniortesst.model.User;
import net.tryhard.juniortesst.repository.UserDAO;
import net.tryhard.juniortesst.util.ValidationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@RequiredArgsConstructor
@Validated
public class UserService {
    private final UserMapper userMapper;
    private final UserDAO userDAO;
    private final ValidationUtils validationUtils;

    public List<UserDTO> searchUsers(UserDTOFIltered userDTOFIltered, Integer pageNumber, Integer pageSize) {
        if (pageSize > 50) {
            pageSize = 50;
        } else if (pageSize < 0) {
            pageSize = 0;
        }
        return userDAO.findFiltered(userDTOFIltered, pageNumber, pageSize)
                .stream().
                map(userMapper::mapUserDTO).
                toList();
    }

    public List<UserDTO> findAll(Integer pageNumber, Integer pageSize) {
        if (pageSize > 50) {
            pageSize = 50;
        } else if (pageSize < 0) {
            pageSize = 0;
        }
        return userDAO.findByDeletedFalse(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(userMapper::mapUserDTO)
                .toList();
    }

    public UserDTO saveUser(UserDTOCreate userDTOCreate) {
        validationUtils.validationRequest(userDTOCreate);
        User user = userDAO.save(userMapper.mapUser(userDTOCreate));
        return userMapper.mapUserDTO(user);

    }

    public UserDTO updateUser(Long id, UserDTOUpdate userDTOUpdate) {
        User user = userDAO.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("user with id " + id + " not found."));
        validationUtils.validationRequest(userDTOUpdate);
        userMapper.updateUserFromDto(userDTOUpdate, user);
        return userMapper.mapUserDTO(userDAO.save(user));
    }

    public void deleteById(Long id) {
        User user = userDAO.findByIdAndDeletedFalse(id).orElseThrow(() -> new NotFoundException("user with id " + id + " not found."));
        user.setDeleted(true);
        userDAO.save(user);
    }
}
