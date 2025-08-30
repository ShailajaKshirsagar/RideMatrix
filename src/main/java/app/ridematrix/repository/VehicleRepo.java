package app.ridematrix.repository;

import app.ridematrix.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle,Integer>
{
}
