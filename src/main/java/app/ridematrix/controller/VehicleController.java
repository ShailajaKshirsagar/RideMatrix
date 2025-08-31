package app.ridematrix.controller;

import app.ridematrix.dto.CreateVehicleRequest;
import app.ridematrix.entity.Vehicle;
import app.ridematrix.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
@Tag(name = "Vehicle Controller", description = "API's to manage Vehicles with residents")
public class VehicleController
{
    @Autowired
    private VehicleService vehicleService;

    //vehicle creation
    @PostMapping("/addVehicle")
    @Operation(summary = "Create new vehicle with existing resident")
    //Here we have used dto and passed it -> dont want to expose the entire entity
    public ResponseEntity<String> createVehicle(@Valid @RequestBody CreateVehicleRequest request) {
        String msg = vehicleService.saveVehicle(request);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

}
