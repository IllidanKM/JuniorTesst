package net.tryhard.juniortesst.repository.custom;

import net.tryhard.juniortesst.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface UserRepositoryCustom {
    public List<UserDTO> filterTable(String firstName, String lastName, String middleName, LocalDate dateOfBirth);
}
