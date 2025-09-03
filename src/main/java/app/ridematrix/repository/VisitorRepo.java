package app.ridematrix.repository;

import app.ridematrix.entity.Visitors;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VisitorRepo extends JpaRepository<Visitors,Integer>
{
    //Query to get user with registraion num
    @Query("SELECT v FROM Visitors v WHERE v.vehicleRegNumber = :vehicleRegNumber")
    Optional<Visitors> findVisitorByRegNum(@Param("vehicleRegNumber") String vehicleRegNumber);

    //query to update end time when user is not active
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE Visitors v SET v.timeOut = :v.timeOut WHERE v.vehicleRegNum = :vehicleRegNum AND v.isActiveVisitor=false")
//    Visitors updateOutTime(@Param("vehicleRegNum") String vehicleRegNum,@Param("timeOut") Visitors timeOut);
    @Modifying
    @Transactional
    @Query("UPDATE Visitors v SET v.timeOut = :timeOut, v.isActiveVisitor = false WHERE v.vehicleRegNumber = :vehicleRegNumber AND v.isActiveVisitor = true")
    int updateOutTime(@Param("vehicleRegNumber") String vehicleRegNumber, @Param("timeOut") LocalDateTime timeOut);

}
