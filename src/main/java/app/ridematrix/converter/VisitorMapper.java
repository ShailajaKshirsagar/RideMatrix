package app.ridematrix.converter;

import app.ridematrix.dto.VisitorResponseDTO;
import app.ridematrix.entity.Visitors;

public class VisitorMapper {

    public static VisitorResponseDTO toDto(Visitors visitor) {
        VisitorResponseDTO.VisitorResponseDTOBuilder builder = VisitorResponseDTO.builder()
                .visitorName(visitor.getVisitorName())
                .vehicleName(visitor.getVehicleName())
                .vehicleRegNum(visitor.getVehicleRegNumber())
                .visitorType(visitor.getVisitorType())
                .visitPurpose(visitor.getVisitPurpose())
                .timeIn(visitor.getTimeIn())
                .timeOut(visitor.getTimeOut())
                .phoneNum(visitor.getPhoneNum())
                .activeVistor(visitor.isActiveVisitor());

        // Add flatNo if resident is present
        if (visitor.getResident() != null) {
            builder.flatNo(visitor.getResident().getFlatNo());
        }
        return builder.build();  // Build at the end
    }
}
