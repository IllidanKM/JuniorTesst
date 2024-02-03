package net.tryhard.juniortesst.mapper;

import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.dto.UserDTOCreate;
import net.tryhard.juniortesst.model.User;
import org.mapstruct.Mapper;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserDTO mapUserDTO(User user);

    public abstract User mapUser(UserDTOCreate userDTOCreate);

}
