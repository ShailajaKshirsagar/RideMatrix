package app.ridematrix.repository;

import app.ridematrix.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepo extends JpaRepository<Resident,Integer>
{
}
