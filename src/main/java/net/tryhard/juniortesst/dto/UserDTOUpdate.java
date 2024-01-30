package net.tryhard.juniortesst.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserDTOUpdate {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateOfBirth;
}

