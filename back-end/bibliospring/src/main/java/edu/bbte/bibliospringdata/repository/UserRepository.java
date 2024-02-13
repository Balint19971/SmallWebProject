package edu.bbte.bibliospringdata.repository;

import edu.bbte.bibliospringdata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

    @Query("FROM User u WHERE u.username = :username")
    public User mindegyNevu(@Param("username") String username);
}
