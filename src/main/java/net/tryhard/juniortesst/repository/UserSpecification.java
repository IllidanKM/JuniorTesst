package net.tryhard.juniortesst.repository;

import net.tryhard.juniortesst.model.User;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

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

}
