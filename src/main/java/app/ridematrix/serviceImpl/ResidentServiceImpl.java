package app.ridematrix.serviceImpl;

import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Vehicle;
import app.ridematrix.entity.Visitors;
import app.ridematrix.repository.ResidentRepo;
import app.ridematrix.repository.VehicleRepo;
import app.ridematrix.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService
{
    @Autowired
    private ResidentRepo residentRepository;

    @Autowired
    private VehicleRepo vehicleRepository;


    @Override
    public String saveResident(Resident resident) {
        //need to save the vehicle activation deactivation time as well

        List<Vehicle> vehicles = resident.getVehicleList();

        if (vehicles != null && !vehicles.isEmpty()) {
            for (Vehicle vehicle : vehicles) {
                vehicle.setResident(resident);
                if (vehicle.isVehicleActive()) {
                    vehicle.setAssociationActivatedAt(LocalDateTime.now());
                    vehicle.setAssociationDeactivatedAt(null);
                } else {
                    vehicle.setAssociationDeactivatedAt(LocalDateTime.now());
                }
            }
        }
        residentRepository.save(resident);
        return "Resident Saved";
    }

    @Override
    public List<Resident> getAllResidents() {
        List<Resident> residentList= residentRepository.findAll();
        return residentList;
    }

    @Override
    public List<Resident> getResidentByName(String fName, String lName) {
      List<Resident> residentList = residentRepository.findResidentByName(fName, lName);
      return residentList;
    }
}
