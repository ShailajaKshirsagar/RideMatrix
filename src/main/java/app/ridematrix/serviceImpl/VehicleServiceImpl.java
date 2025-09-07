package app.ridematrix.serviceImpl;

import app.ridematrix.dto.CreateVehicleRequest;
import app.ridematrix.dto.GetResidentDataRequest;
import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Vehicle;
import app.ridematrix.exception.InvalidRegistrationNum;
import app.ridematrix.exception.ResourceNotFoundException;
import app.ridematrix.repository.ResidentRepo;
import app.ridematrix.repository.VehicleRepo;
import app.ridematrix.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        //and mapped to vehicle entity using request , setter and getter
        if (residentOpt.isPresent()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setRegistrationNum(request.getRegistrationNum());
            vehicle.setVColor(request.getVcolor());
            vehicle.setVType(Vehicle.VehicleType.valueOf(request.getVtype()));
            vehicle.setVehicleActive(request.isVehicleActive());
            // Set activation/deactivation time based on vehicle active status
            if (request.isVehicleActive()) {
                vehicle.setAssociationActivatedAt(LocalDateTime.now());
                vehicle.setAssociationDeactivatedAt(null);
            } else {
                vehicle.setAssociationDeactivatedAt(LocalDateTime.now());
                vehicle.setAssociationActivatedAt(null);
            }
            vehicle.setResident(residentOpt.get());
            vehicleRepository.save(vehicle);
            return "Vehicle saved successfully.";
        } else {
            return "Resident not found. Vehicle cannot be saved.";
        }
    }

    @Override
    public GetResidentDataRequest getResidentByRegNum(String registrationNum) {

        if (!registrationNum.matches("^[A-Z0-9]{4}-[A-Z]{2}-\\d{4}$")) {
            throw new InvalidRegistrationNum("Invalid Registration Number!! Must be equal to 10");
        }
        Optional<Vehicle> vehicleByRegistrationNum = vehicleRepository.findVehicleByRegistrationNum(registrationNum);

        if(vehicleByRegistrationNum.isPresent()){
            Resident residentByRegnum = vehicleByRegistrationNum.get().getResident();

            GetResidentDataRequest residentDataRequest = GetResidentDataRequest.builder()
                    .id(residentByRegnum.getId())
                    .fName(residentByRegnum.getFName())
                    .lName(residentByRegnum.getLName())
                    .residentType(residentByRegnum.getResidentType())
                    .email(residentByRegnum.getEmail())
                    .flatNo(residentByRegnum.getFlatNo())
                    .mobNo(residentByRegnum.getMobNo())
                    .build();
            return residentDataRequest;
        }else{
            throw new ResourceNotFoundException("Vehicle with registration number '" + registrationNum + "' not found.");
        }
    }
}
