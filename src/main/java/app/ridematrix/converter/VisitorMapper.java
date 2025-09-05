package app.ridematrix.converter;

import app.ridematrix.dto.VisitorResponseDTO;
import app.ridematrix.entity.Visitors;

public class VisitorMapper {

    public static VisitorResponseDTO toDto(Visitors visitor) {
        return VisitorResponseDTO.builder()
                .visitorName(visitor.getVisitorName())
                .vehicleName(visitor.getVehicleName())
                .vehicleRegNum(visitor.getVehicleRegNumber())
                .visitorType(visitor.getVisitorType())
                .visitPurpose(visitor.getVisitPurpose())
                .timeIn(visitor.getTimeIn())
                .timeOut(visitor.getTimeOut())
                .phoneNum(visitor.getPhoneNum())
                .activeVistor(visitor.isActiveVisitor())
                .build();
    }
}
