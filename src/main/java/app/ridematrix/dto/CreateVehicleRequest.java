package app.ridematrix.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

//This is dto class
@Setter
@Getter
public class CreateVehicleRequest
{
    @NotNull(message = "Registration num is required")
    private String registrationNum;
    private String vcolor;
    private String vtype;
    private boolean vehicleActive;
    private int residentId;  // only pass resident ID here
}
