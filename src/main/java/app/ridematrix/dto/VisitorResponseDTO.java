package app.ridematrix.dto;

import app.ridematrix.entity.Visitors;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Builder annotation to map dto to entity using mapper -> converter->visitorMapper
//This dto is for get vistor details and REsident details by reg number
public class VisitorResponseDTO
{
    private String visitorName;
    private String vehicleName;
    private String vehicleRegNum;
    private Visitors.VisitorType visitorType;
    private String visitPurpose;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;
    private long phoneNum;
    public boolean activeVistor;
    private GetResidentDataRequest getResidentDataRequest;
}
