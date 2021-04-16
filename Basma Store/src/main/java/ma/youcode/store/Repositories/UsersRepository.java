package ma.youcode.store.Repositories;

import ma.youcode.store.Modeles.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
   @Query("SELECT u FROM Users u WHERE u.email = :email")
   public Users getUserByEmail(@Param("email") String email);
}
