package app.ridematrix.dto;

//This is for downloading or storing visitor data from database into excel file with resident flat no and resident name

import app.ridematrix.entity.Visitors;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
//Mapper of this is in ConverterPackage
public class VisitorExcelDataDTO {

    private String visitorName;
    private String vehicleName;
    private Visitors.VisitorType visitorType;
    private String vehicleRegNum;
    private long phoneNum;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private String visitDuration;
    private String visitPurpose;
    private String flatNo;
    private String residentName;
}
