package net.tryhard.juniortesst.service;

import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.dto.UserDTOCreate;
import net.tryhard.juniortesst.dto.UserDTOUpdate;
import net.tryhard.juniortesst.exception.NotAllParametrsException;
import net.tryhard.juniortesst.exception.NotFoundException;
import net.tryhard.juniortesst.mapper.UserMapper;
import net.tryhard.juniortesst.model.User;
import net.tryhard.juniortesst.repository.UserDAO;
import net.tryhard.juniortesst.repository.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserDAO userDAO;


    public List<UserDTO> searchUsers(String firstName, String lastName, String middleName, String dateOfBirth) {
        Specification<User> spec = UserSpecification.notDeleted();

        if (lastName != null) {
            spec = spec.and(UserSpecification.lastNameLike(lastName));
        }

        if (firstName != null) {
            spec = spec.and(UserSpecification.firstNameLike(firstName));
        }

        if (middleName != null) {
            spec = spec.and(UserSpecification.middleNameLike(middleName));
        }

        if (dateOfBirth != null) {
            LocalDate birthDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            spec = spec.and(UserSpecification.dateOfBirthEqual(birthDate));
        }

        return userDAO.findAll(spec).stream().map(userMapper::mapUserDTO).toList();
    }

    public List<UserDTO> findAll() {
        return userDAO.findByDeletedFalse()
                .stream()
                .map(userMapper::mapUserDTO)
                .toList();
    }

    public UserDTO saveUser(UserDTOCreate userDTOCreate) {
        if (userDTOCreate.getDateOfBirth()== null|| userDTOCreate.getFirstName()== null || userDTOCreate.getLastName() == null || userDTOCreate.getMiddleName() == null ){
            throw new NotAllParametrsException("To create a user, the following parameters are required: \"firstName\", \"lastName\", \"middleName\", and \"dateOfBirth\".");
        }
        User user = userDAO.save(userMapper.mapUser(userDTOCreate));
        return userMapper.mapUserDTO(user);

    }

    public UserDTO updateUser(Long id, UserDTOUpdate userDTOUpdate) {
        User user = userDAO.findById(id).orElseThrow(() -> new NotFoundException("user with id " + id + " not found."));
        if (user.isDeleted()) {
            throw new NotFoundException("user with id " + id + " was deleted.");
        }
        if (userDTOUpdate.getDateOfBirth()== null|| userDTOUpdate.getFirstName()== null || userDTOUpdate.getLastName() == null || userDTOUpdate.getMiddleName() == null ){
            throw new NotAllParametrsException("To update a user, the following parameters are required: \"firstName\", \"lastName\", \"middleName\", and \"dateOfBirth\".");
        }
        user.setFirstName(userDTOUpdate.getFirstName());
        user.setLastName(userDTOUpdate.getLastName());
        user.setMiddleName(userDTOUpdate.getMiddleName());
        user.setDateOfBirth(userDTOUpdate.getDateOfBirth());
        return userMapper.mapUserDTO(userDAO.save(user));
    }

    public void deleteById(Long id) {
        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDeleted(true);
            userDAO.save(user);
        } else {
            throw new NotFoundException("User with id " + id + " does not exist");
        }
    }
}
