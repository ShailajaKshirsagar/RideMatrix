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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService
{
    @Autowired
    private VehicleRepo vehicleRepository;

    @Autowired
    private ResidentRepo residentRepository;

    @Override
    public String saveVehicle(CreateVehicleRequest request) {
        log.info("Attempting to save vehicle with registration number: {}", request.getRegistrationNum());

        Optional<Resident> residentOpt = residentRepository.findById(request.getResidentId());

        if (residentOpt.isPresent()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setRegistrationNum(request.getRegistrationNum());
            vehicle.setVColor(request.getVcolor());
            vehicle.setVType(Vehicle.VehicleType.valueOf(request.getVtype()));
            vehicle.setVehicleActive(request.isVehicleActive());

            if (request.isVehicleActive()) {
                vehicle.setAssociationActivatedAt(LocalDateTime.now());
                vehicle.setAssociationDeactivatedAt(null);
                log.info("Vehicle {} activated at {}", vehicle.getRegistrationNum(), vehicle.getAssociationActivatedAt());
            } else {
                vehicle.setAssociationDeactivatedAt(LocalDateTime.now());
                vehicle.setAssociationActivatedAt(null);
                log.info("Vehicle {} deactivated at {}", vehicle.getRegistrationNum(), vehicle.getAssociationDeactivatedAt());
            }

            vehicle.setResident(residentOpt.get());
            vehicleRepository.save(vehicle);

            log.info("Vehicle with registration number {} saved successfully", vehicle.getRegistrationNum());
            return "Vehicle saved successfully.";
        } else {
            log.warn("Resident with ID {} not found. Cannot save vehicle {}", request.getResidentId(), request.getRegistrationNum());
            return "Resident not found. Vehicle cannot be saved.";
        }
    }

    @Override
    public GetResidentDataRequest getResidentByRegNum(String registrationNum) {
        log.info("Fetching resident data for vehicle registration number: {}", registrationNum);

        if (!registrationNum.matches("^[A-Z0-9]{4}-[A-Z]{2}-\\d{4}$")) {
            log.error("Invalid registration number format received: {}", registrationNum);
            throw new InvalidRegistrationNum("Invalid Registration Number!! Must be equal to 10");
        }

        Optional<Vehicle> vehicleByRegistrationNum = vehicleRepository.findVehicleByRegistrationNum(registrationNum);

        if (vehicleByRegistrationNum.isPresent()) {
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

            log.info("Resident data found for vehicle registration number: {}", registrationNum);
            return residentDataRequest;
        } else {
            log.warn("Vehicle with registration number {} not found", registrationNum);
            throw new ResourceNotFoundException("Vehicle with registration number '" + registrationNum + "' not found.");
        }
    }
}