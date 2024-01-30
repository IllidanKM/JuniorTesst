package net.tryhard.juniortesst.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateOfBirth;
}
