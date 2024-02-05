package net.tryhard.juniortesst.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOFIltered {
        private String firstName;
        private String lastName;
        private String middleName;
        private String dateOfBirth;
}
