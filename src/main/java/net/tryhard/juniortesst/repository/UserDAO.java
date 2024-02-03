package net.tryhard.juniortesst.repository;

import net.tryhard.juniortesst.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Primary
public interface UserDAO extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    List<User> findByDeletedFalse();

}
