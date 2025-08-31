package app.ridematrix.repository;

import app.ridematrix.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//Here we write queries
public interface VehicleRepo extends JpaRepository<Vehicle,Integer>
{
    //Query to get user with registraion num
    @Query("SELECT v FROM Vehicle v WHERE v.registrationNum = :registrationNum")
    Optional<Vehicle> findVehicleByRegistrationNum(@Param("registrationNum") String registrationNum);

}
