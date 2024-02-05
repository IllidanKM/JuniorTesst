package net.tryhard.juniortesst.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOUpdate {
    @NotNull(message = "First Name cannot be null")
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;

    @NotNull(message = "Middle Name cannot be null")
    @NotBlank(message = "Middle Name cannot be blank")
    private String middleName;

    @NotNull(message = "Date Of Birth cannot be null")
    private LocalDate dateOfBirth;
}

