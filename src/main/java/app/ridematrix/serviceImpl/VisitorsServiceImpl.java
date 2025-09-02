package app.ridematrix.serviceImpl;

import app.ridematrix.dto.VisitorRequestDto;
import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Visitors;
import app.ridematrix.repository.ResidentRepo;
import app.ridematrix.repository.VisitorRepo;
import app.ridematrix.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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
        visitors.setActiveVisitor(dto.isActiveVisitor());
        visitors.setTimeIn(LocalDateTime.now());
        visitors.setPhoneNum(dto.getPhoneNum());
        visitors.setVisitPurpose(dto.getVisitPurpose());
        visitors.setVehicleName(dto.getVehicleName());
        visitors.setVehicleName(dto.getVehicleName());
        visitors.setResident(resident.get());
        visitorRepository.save(visitors);

        return "Visitor saved";
    }
}
