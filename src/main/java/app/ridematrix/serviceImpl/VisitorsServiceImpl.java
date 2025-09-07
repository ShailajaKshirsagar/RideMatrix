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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VisitorsServiceImpl implements VisitorService {

    //inject repo in this
    @Autowired
    private VisitorRepo visitorRepository;
    @Autowired
    private ResidentRepo residentRepository;

    @Override
    public String saveVisitor(VisitorRequestDto dto) {
        log.info("Attempting to save visitor for flatNo: {}", dto.getFlatNo());

        Optional<Resident> resident = residentRepository.findByFlatNo(dto.getFlatNo());

        if (resident.isEmpty()) {
            log.warn("Resident not found with flatNo: {}", dto.getFlatNo());
            throw new RuntimeException("Resident not found for flat number: " + dto.getFlatNo());
        }

        Visitors visitors = new Visitors();
        visitors.setVisitorName(dto.getVisitorName());
        visitors.setVehicleRegNumber(dto.getVehicleRegNumber());
        visitors.setVisitorType(dto.getVisitorType());
        visitors.setTimeIn(LocalDateTime.now());
        visitors.setPhoneNum(dto.getPhoneNum());
        visitors.setVisitPurpose(dto.getVisitPurpose());
        visitors.setVehicleName(dto.getVehicleName());
        visitors.setResident(resident.get());

        visitorRepository.save(visitors);
        log.info("Visitor {} saved successfully for flatNo: {}", dto.getVisitorName(), dto.getFlatNo());

        return "Visitor saved";
    }

    @Override
    public VisitorResponseDTO findVisitorResidentByRegNum(String vehicleRegNum) {
        log.info("Fetching visitor details by vehicleRegNum: {}", vehicleRegNum);

        Optional<Visitors> visitorByRegNum = visitorRepository.findVisitorByRegNum(vehicleRegNum);
        if (visitorByRegNum.isPresent()) {
            log.info("Visitor found with vehicleRegNum: {}", vehicleRegNum);
            // mapping and returning DTO as before...
            return VisitorMapper.toDto(visitorByRegNum.get());
        }

        log.warn("Visitor not found with vehicleRegNum: {}", vehicleRegNum);
        throw new RuntimeException("Visitor not found with vehicle registration number: " + vehicleRegNum);
    }

    @Override
    public String updateOutTime(String vehicleRegNum) {
        log.info("Updating out time for visitor with vehicleRegNum: {}", vehicleRegNum);

        Optional<Visitors> visitor = visitorRepository.findVisitorByRegNum(vehicleRegNum);

        if (visitor.isPresent()) {
            Visitors v = visitor.get();
            v.setTimeOut(LocalDateTime.now());
            visitorRepository.save(v);
            log.info("Visitor exit time updated for vehicleRegNum: {}", vehicleRegNum);
            return "Visitor exit time and duration updated";
        }

        log.warn("Visitor not found with vehicleRegNum: {}", vehicleRegNum);
        throw new RuntimeException("Visitor not found with vehicle registration number: " + vehicleRegNum);
    }

    @Override
    public List<VisitorResponseDTO> findVisitorType(List<Visitors.VisitorType> visitorType) {
        log.info("Fetching visitors with types: {}", visitorType);

        List<Visitors> visitors;
        if (visitorType == null || visitorType.isEmpty()) {
            visitors = visitorRepository.findAllVisitors();
            log.info("Fetched all visitors as no specific visitorType provided");
        } else {
            visitors = visitorRepository.findVisitorType(visitorType);
            log.info("Fetched visitors filtered by visitorType");
        }

        List<VisitorResponseDTO> dtoList = visitors.stream()
                .map(VisitorMapper::toDto)
                .collect(Collectors.toList());

        log.info("Total visitors fetched: {}", dtoList.size());

        return dtoList;
    }
}