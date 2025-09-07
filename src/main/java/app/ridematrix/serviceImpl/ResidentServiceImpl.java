package app.ridematrix.serviceImpl;

import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Vehicle;
import app.ridematrix.entity.Visitors;
import app.ridematrix.repository.ResidentRepo;
import app.ridematrix.repository.VehicleRepo;
import app.ridematrix.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ResidentServiceImpl implements ResidentService
{
    @Autowired
    private ResidentRepo residentRepository;

    @Autowired
    private VehicleRepo vehicleRepository;


    @Override
    public String saveResident(Resident resident) {
        log.info("Saving resident with ID: {}", resident.getId());

        List<Vehicle> vehicles = resident.getVehicleList();

        if (vehicles != null && !vehicles.isEmpty()) {
            for (Vehicle vehicle : vehicles) {
                vehicle.setResident(resident);
                if (vehicle.isVehicleActive()) {
                    vehicle.setAssociationActivatedAt(LocalDateTime.now());
                    vehicle.setAssociationDeactivatedAt(null);
                    log.info("Activated vehicle with registration number: {}", vehicle.getRegistrationNum());
                } else {
                    vehicle.setAssociationDeactivatedAt(LocalDateTime.now());
                    log.info("Deactivated vehicle with registration number: {}", vehicle.getRegistrationNum());
                }
            }
        } else {
            log.info("No vehicles associated with resident ID: {}", resident.getId());
        }

        residentRepository.save(resident);
        log.info("Resident with ID: {} saved successfully", resident.getId());
        return "Resident Saved";
    }

    @Override
    public List<Resident> getAllResidents() {
        log.info("Fetching all residents");
        List<Resident> residentList = residentRepository.findAll();
        log.info("Fetched {} residents", residentList.size());
        return residentList;
    }

    @Override
    public List<Resident> getResidentByName(String fName, String lName) {
        log.info("Searching residents by name: firstName='{}', lastName='{}'", fName, lName);
        List<Resident> residentList = residentRepository.findResidentByName(fName, lName);
        log.info("Found {} residents matching the name criteria", residentList.size());
        return residentList;
    }
}