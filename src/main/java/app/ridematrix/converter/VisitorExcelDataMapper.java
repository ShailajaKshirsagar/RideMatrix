package app.ridematrix.converter;

import app.ridematrix.dto.VisitorExcelDataDTO;
import app.ridematrix.entity.Visitors;

public class VisitorExcelDataMapper
{
    public static VisitorExcelDataDTO toDto(Visitors visitor){

        return VisitorExcelDataDTO.builder()
                .visitorName(visitor.getVisitorName())
                .visitorType(visitor.getVisitorType())
                .visitPurpose(visitor.getVisitPurpose())
                .vehicleName(visitor.getVehicleName())
                .vehicleRegNum(visitor.getVehicleRegNumber())
                .visitDuration(visitor.getVisitDuration())
                .timeIn(visitor.getTimeIn())
                .timeOut(visitor.getTimeOut())
                .phoneNum(visitor.getPhoneNum())
                .flatNo(visitor.getResident().getFlatNo())
                .residentName(visitor.getResident().getFName())
                .build();
    }
}
