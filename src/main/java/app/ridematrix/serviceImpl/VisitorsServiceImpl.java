package app.ridematrix.serviceImpl;

import app.ridematrix.converter.VisitorMapper;
import app.ridematrix.dto.GetResidentDataRequest;
import app.ridematrix.dto.VisitorRequestDto;
import app.ridematrix.dto.VisitorResponseDTO;
import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Visitors;
import app.ridematrix.repository.ResidentRepo;
import app.ridematrix.repository.VisitorRepo;
import app.ridematrix.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitorsServiceImpl implements VisitorService {

    //inject repo in this
    @Autowired
    private VisitorRepo visitorRepository;
    @Autowired
    private ResidentRepo residentRepository;

    @Override
    public String saveVisitor(VisitorRequestDto dto) {

        Optional<Resident> resident = residentRepository.findByFlatNo(dto.getFlatNo());

        Visitors visitors = new Visitors();
        visitors.setVisitorName(dto.getVisitorName());
        visitors.setVehicleRegNumber(dto.getVehicleRegNumber());
        visitors.setVisitorType(dto.getVisitorType());
        visitors.setTimeIn(LocalDateTime.now());
        visitors.setPhoneNum(dto.getPhoneNum());
        visitors.setVisitPurpose(dto.getVisitPurpose());
        visitors.setVehicleName(dto.getVehicleName());
        visitors.setVehicleName(dto.getVehicleName());
        visitors.setResident(resident.get());
        visitorRepository.save(visitors);

        return "Visitor saved";
    }

    @Override
    public VisitorResponseDTO findVisitorResidentByRegNum(String vehicleRegNum) {

        Optional<Visitors> visitorByRegNum = visitorRepository.findVisitorByRegNum(vehicleRegNum);
        if (visitorByRegNum.isPresent()) {

            Visitors visitors = visitorByRegNum.get(); // find associated visitor use get
            Resident resident = visitorByRegNum.get().getResident(); //find associated resident

            //mapping data in dto
            VisitorResponseDTO dto = new VisitorResponseDTO();
            dto.setVisitorName(visitors.getVisitorName());
            dto.setVisitorType(visitors.getVisitorType());
            dto.setVehicleName(visitors.getVehicleName());
            dto.setPhoneNum(visitors.getPhoneNum());
            dto.setVehicleRegNum(visitors.getVehicleRegNumber());
            dto.setTimeIn(visitors.getTimeIn());
            dto.setTimeOut(visitors.getTimeOut());
            dto.setVisitPurpose(visitors.getVisitPurpose());
            dto.setActiveVistor(visitors.isActiveVisitor());

            //mapping data in dto
            GetResidentDataRequest residentData = new GetResidentDataRequest();
            residentData.setId(resident.getId());
            residentData.setFName(resident.getFName());
            residentData.setLName(resident.getLName());
            residentData.setFlatNo(resident.getFlatNo());
            residentData.setMobNo(resident.getMobNo());
            residentData.setEmail(resident.getEmail());
            residentData.setResidentType(resident.getResidentType());

            dto.setGetResidentDataRequest(residentData);

            return dto;
        }
        throw new RuntimeException("Visitor not found with vehicle registration number: " + vehicleRegNum);
    }

    //update out time by vehicle num
    @Override
    public String updateOutTime(String vehicleRegNum) {
        LocalDateTime now = LocalDateTime.now(); // auto time
        int updated = visitorRepository.updateOutTime(vehicleRegNum, now);
        return "Time out updated";

    }

    @Override
    public List<VisitorResponseDTO> findVisitorType(List<Visitors.VisitorType> visitorType) {
        List<Visitors> visitors;
        if (visitorType.isEmpty() || visitorType==null){
            visitors = visitorRepository.findAll();
        } else{
            visitors = visitorRepository.findVisitorType(visitorType);
        }
        // here we have to convert visitors into visitorresponse
        return visitors.stream()
                .map(VisitorMapper::toDto)
                .collect(Collectors.toList());
    }
}