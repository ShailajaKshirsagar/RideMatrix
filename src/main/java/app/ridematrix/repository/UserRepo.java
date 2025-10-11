package app.ridematrix.repository;

import app.ridematrix.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<CustomUser,Integer>
{
    CustomUser findByUsername(String username);
}
