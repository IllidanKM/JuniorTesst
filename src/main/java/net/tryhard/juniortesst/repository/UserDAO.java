package net.tryhard.juniortesst.repository;

import net.tryhard.juniortesst.dto.UserDTOFIltered;
import net.tryhard.juniortesst.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface UserDAO extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    List<User> findByDeletedFalse(PageRequest pageRequest);
    default Page<User> findFiltered(UserDTOFIltered userDTOFIltered,Integer pageNumber, Integer pageSize){
        Specification<User> spec = UserSpecification.findUsers(userDTOFIltered);
        return findAll(spec,PageRequest.of(pageNumber,pageSize));

    }
    Optional<User> findByIdAndDeletedFalse(Long id);

}
