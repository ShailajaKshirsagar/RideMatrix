package app.ridematrix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

//This is dto class
@Setter
@Getter
public class CreateVehicleRequest
{
    @NotBlank(message = "Registration num is required")
    private String registrationNum;
    private String vcolor;
    @NotBlank(message = "Vehicle Type is Required")
    private String vtype;
    private boolean vehicleActive;
    private int residentId;  // only pass resident ID here
}
