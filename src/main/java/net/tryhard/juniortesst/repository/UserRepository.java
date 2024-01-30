package net.tryhard.juniortesst.repository;

import net.tryhard.juniortesst.model.User;
import net.tryhard.juniortesst.repository.custom.UserRepositoryCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
@Primary
public interface UserRepository extends JpaRepository<User,Long>, UserRepositoryCustom {
}
