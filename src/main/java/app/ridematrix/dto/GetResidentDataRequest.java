package app.ridematrix.dto;

import app.ridematrix.entity.Resident;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//DTO FOr resident data
@Setter
@Getter
@ToString
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
}
