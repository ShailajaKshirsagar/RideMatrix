package app.ridematrix.repository;

import app.ridematrix.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResidentRepo extends JpaRepository<Resident,Integer>
{
    //get resident by name jpql query
    //Ilike -> ignore cases (captal,small)
    @Query(value = "SELECT * FROM resident r WHERE " +
            "(:fName IS NULL OR r.f_name ILIKE :fName) AND " +
            "(:lName IS NULL OR r.l_name ILIKE :lName)", nativeQuery = true)
    List<Resident> findResidentByName(
            @Param("fName") String fName,
            @Param("lName") String lName
    );

    //find by flat no
    @Query("SELECT r FROM Resident r WHERE LOWER(r.flatNo) = LOWER(:flatNo)")
    Optional<Resident> findByFlatNo(@Param("flatNo") String flatNo);
}
