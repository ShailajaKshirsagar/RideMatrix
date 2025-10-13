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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
@Slf4j
@Tag(name = "Vehicle Controller", description = "API's to manage Vehicles with residents")
public class VehicleController
{
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ResidentService residentService;

    @PostMapping("/addVehicle")
    @Operation(summary = "Create new vehicle with existing resident")
    public ResponseEntity<String> createVehicle(@Valid @RequestBody CreateVehicleRequest request) {
        log.info("Received request to add vehicle for residentId: {}, regNum: {}");
        String msg = vehicleService.saveVehicle(request);
        log.info("Vehicle creation result: {}", msg);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/getUserData/{registrationNum}")
    @Operation(summary = "Get user Details with Registration number")
    public ResponseEntity<GetResidentDataRequest> getResidentDataByRegNum(
            @Valid @PathVariable("registrationNum") String registrationNum){

        log.info("Fetching resident data using vehicle registration number: {}", registrationNum);
        GetResidentDataRequest getResidentbynum = vehicleService.getResidentByRegNum(registrationNum);
        log.info("Resident data fetched successfully for registration number: {}", registrationNum);
        return new ResponseEntity<>(getResidentbynum,HttpStatus.OK);
    }
}
