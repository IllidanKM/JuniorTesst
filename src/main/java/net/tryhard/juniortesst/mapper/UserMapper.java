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

    public abstract UserDTO mapUserDTO(User user) ;

    public abstract UserDTO mapUserDTO(UserDTOUpdate userDTOUpdate) ;
    public abstract UserDTO mapUserDTO(UserDTOCreate userDTOCreate) ;
    public abstract User mapUser(UserDTOUpdate userDTOUpdate) ;
    public abstract User mapUser(UserDTOCreate userDTOCreate) ;
}
