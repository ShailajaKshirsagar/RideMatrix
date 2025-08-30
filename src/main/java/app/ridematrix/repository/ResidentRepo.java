package app.ridematrix.repository;

import app.ridematrix.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepo extends JpaRepository<Resident,Integer>
{
    @Query("SELECT r FROM Resident r WHERE " +
            "(:fName IS NULL OR r.fName = :fName) AND " +
            "(:lName IS NULL OR r.lName = :lName)")
    List<Resident> findResidentByName(
            @Param("fName") String fName,
            @Param("lName") String lName
    );
}
