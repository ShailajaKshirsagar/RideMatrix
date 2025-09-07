package app.ridematrix.dto;

import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Vehicle;
import lombok.*;

import java.util.List;

//DTO FOr resident data
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetResidentDataRequest
{
    private int id;
    private String fName;
    private String lName;
    private String flatNo;
    private long mobNo;
    private String email;
    private Resident.ResidentType residentType;
    public enum ResidentType {
        OWNER,
        TENANT
    }
    private
    List<Vehicle> vehicleList;
}
