package app.ridematrix.repository;

import app.ridematrix.dto.VisitorResponseDTO;
import app.ridematrix.entity.Visitors;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepo extends JpaRepository<Visitors,Integer>
{
    //Query to get user with registraion num
    @Query("SELECT v FROM Visitors v WHERE v.vehicleRegNumber = :vehicleRegNumber")
    Optional<Visitors> findVisitorByRegNum(@Param("vehicleRegNumber") String vehicleRegNumber);

    //query to update end time when user is not active
    @Modifying
    @Transactional
    @Query("UPDATE Visitors v SET v.timeOut = :timeOut, v.isActiveVisitor = false WHERE v.vehicleRegNumber = :vehicleRegNumber AND v.isActiveVisitor = true")
    int updateOutTime(@Param("vehicleRegNumber") String vehicleRegNumber, @Param("timeOut") LocalDateTime timeOut);

    //for finding the type of visitor
    @Query("SELECT v FROM Visitors v WHERE v.isActiveVisitor = true AND v.visitorType IN :visitorType")
    List<Visitors> findVisitorType(@Param("visitorType")List<Visitors.VisitorType> visitorType);

    @Query("SELECT v FROM Visitors v WHERE v.isActiveVisitor = true")
    List<Visitors> findAllVisitors();

    //find visitor with null visitDuration
    @Query("SELECT v FROM Visitors v WHERE v.visitDuration IS null")
    List<Visitors> findVisitorWithNullVisitDuration();
}
//while writing jpql query -> use entities exact field name
//in case of native query -> use database column name
