package net.tryhard.juniortesst.mapper;

import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.dto.UserDTOCreate;
import net.tryhard.juniortesst.dto.UserDTOUpdate;
import net.tryhard.juniortesst.model.User;
import org.mapstruct.Mapper;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")

public abstract class UserMapper {
    public abstract User mapUser(UserDTO userDTO);

    public  UserDTO mapUserDTO(User user){
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName( user.getFirstName() );
        userDTO.setLastName( user.getLastName() );
        userDTO.setMiddleName( user.getMiddleName() );
        userDTO.setDateOfBirth( user.getDateOfBirth() );
        userDTO.setId(user.getId());

        return userDTO;

    }

    public abstract UserDTO mapUserDTO(UserDTOUpdate userDTOUpdate) ;
    public abstract UserDTO mapUserDTO(UserDTOCreate userDTOCreate) ;
    public abstract User mapUser(UserDTOUpdate userDTOUpdate) ;
    public User mapUser(UserDTOCreate userDTOCreate) {
        if ( userDTOCreate == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( userDTOCreate.getFirstName() );
        user.setLastName( userDTOCreate.getLastName() );
        user.setMiddleName( userDTOCreate.getMiddleName() );
        user.setDateOfBirth( userDTOCreate.getDateOfBirth() );


        return user;
    }
}
