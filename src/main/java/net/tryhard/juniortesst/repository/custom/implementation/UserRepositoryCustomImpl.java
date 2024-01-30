package net.tryhard.juniortesst.repository.custom.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import net.tryhard.juniortesst.dto.UserDTO;
import net.tryhard.juniortesst.mapper.UserMapper;
import net.tryhard.juniortesst.model.User;
import net.tryhard.juniortesst.repository.custom.UserRepositoryCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @PersistenceContext
    private final EntityManager em;
    private final UserMapper userMapper;


    public List<UserDTO> filterTable(String firstName, String lastName, String middleName, LocalDate dateOfBirth){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate predicate = criteriaBuilder.conjunction();


        if (lastName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("lastName"), lastName));
        }

        if (firstName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("firstName"), firstName));
        }
        if (middleName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("middleName"), middleName));
        }
        if (dateOfBirth != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("dateOfBirth"), dateOfBirth));
        }

        criteriaQuery.where(predicate);

        TypedQuery<User> typedQuery = em.createQuery(criteriaQuery);
        List<User> users = typedQuery.getResultList();

        return users.stream()
                .map(userMapper::mapUserDTO)
                .toList();
    }



}
