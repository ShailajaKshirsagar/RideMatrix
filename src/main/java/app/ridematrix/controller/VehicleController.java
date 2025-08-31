package app.ridematrix.controller;

import app.ridematrix.dto.CreateVehicleRequest;
import app.ridematrix.dto.GetResidentDataRequest;
import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Vehicle;
import app.ridematrix.service.ResidentService;
import app.ridematrix.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Vehicle Controller", description = "API's to manage Vehicles with residents")
public class VehicleController
{
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ResidentService residentService;

    //vehicle creation
    @PostMapping("/addVehicle")
    @Operation(summary = "Create new vehicle with existing resident")
    //Here we have used dto and passed it -> dont want to expose the entire entity
    public ResponseEntity<String> createVehicle(@Valid @RequestBody CreateVehicleRequest request) {
        String msg = vehicleService.saveVehicle(request);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    //get user details only using registration number not vehicle details .
    @GetMapping("/getUserData/{registrationNum}")
    @Operation(summary = "Get user Details with Registration number")
    public ResponseEntity<GetResidentDataRequest> getResidentDataByRegNum(
            @Valid @PathVariable("registrationNum") String registrationNum){
       GetResidentDataRequest getResidentbynum = vehicleService.getResidentByRegNum(registrationNum);
       return new ResponseEntity<>(getResidentbynum,HttpStatus.OK);
    }
}
