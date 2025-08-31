package app.ridematrix.serviceImpl;

import app.ridematrix.dto.CreateVehicleRequest;
import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Vehicle;
import app.ridematrix.repository.ResidentRepo;
import app.ridematrix.repository.VehicleRepo;
import app.ridematrix.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService
{
    @Autowired
    private VehicleRepo vehicleRepository;

    @Autowired
    private ResidentRepo residentRepository;

    @Override
    public String saveVehicle(CreateVehicleRequest request) {
        Optional<Resident> residentOpt = residentRepository.findById(request.getResidentId());

        //here we need to create the object of vehicle because createvehicle reuqest is not and entity
        if (residentOpt.isPresent()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setRegistrationNum(request.getRegistrationNum());
            vehicle.setVColor(request.getVcolor());
            vehicle.setVType(Vehicle.VehicleType.valueOf(request.getVtype())); // assumes valid string input
            vehicle.setVehicleActive(request.isVehicleActive());
            vehicle.setResident(residentOpt.get());
            vehicleRepository.save(vehicle);
            return "Vehicle saved successfully.";
        } else {
            return "Resident not found. Vehicle cannot be saved.";
        }
    }
}
