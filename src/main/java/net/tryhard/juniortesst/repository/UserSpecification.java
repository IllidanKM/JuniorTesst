package net.tryhard.juniortesst.repository;

import net.tryhard.juniortesst.dto.UserDTOFIltered;
import net.tryhard.juniortesst.model.User;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserSpecification {

    public static Specification<User> lastNameLike(String lastName) {
        return (root, query, cb) -> cb.like(root.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<User> firstNameLike(String firstName) {
        return (root, query, cb) -> cb.like(root.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<User> middleNameLike(String middleName) {
        return (root, query, cb) -> cb.like(root.get("middleName"), "%" + middleName + "%");
    }

    public static Specification<User> dateOfBirthEqual(LocalDate dateOfBirth) {
        return (root, query, cb) -> cb.equal(root.get("dateOfBirth"), dateOfBirth);
    }

    public static Specification<User> notDeleted() {
        return (root, query, cb) -> cb.equal(root.get("deleted"), false);
    }

    public static Specification<User> findUsers(UserDTOFIltered userDTOFIltered){
        Specification<User> spec = UserSpecification.notDeleted();

        if (userDTOFIltered.getLastName() != null) {
            spec = spec.and(UserSpecification.lastNameLike(userDTOFIltered.getLastName()));
        }

        if (userDTOFIltered.getFirstName() != null) {
            spec = spec.and(UserSpecification.firstNameLike(userDTOFIltered.getFirstName()));
        }

        if (userDTOFIltered.getMiddleName() != null) {
            spec = spec.and(UserSpecification.middleNameLike(userDTOFIltered.getMiddleName()));
        }

        if (userDTOFIltered.getDateOfBirth() != null) {
            LocalDate birthDate = LocalDate.parse(userDTOFIltered.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            spec = spec.and(UserSpecification.dateOfBirthEqual(birthDate));
        }
        return spec;
    }

}
