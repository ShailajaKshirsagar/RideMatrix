package app.ridematrix.dto;

import app.ridematrix.entity.Visitors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
//Builder annotation to map dto to entity using mapper -> converter->visitorMapper
//This dto is for get vistor details and REsident details by reg number
//This json include will exclude all fields that are null from the response. but will display if it has value
//mapper in converter package
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private GetResidentDataRequest getResidentDataRequest;
    private String flatNo;

}
