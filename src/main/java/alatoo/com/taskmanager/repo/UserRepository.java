package alatoo.com.taskmanager.repo;

import alatoo.com.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByDeletedDateIsNull();
    Optional<User> findByUsername(String username);
}
