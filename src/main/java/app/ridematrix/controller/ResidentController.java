package app.ridematrix.controller;

import app.ridematrix.converter.ResidentMapper;
import app.ridematrix.converter.ResidentMapperWithVehicle;
import app.ridematrix.dto.GetResidentDataRequest;
import app.ridematrix.entity.Resident;
import app.ridematrix.service.ResidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/residents")
@Tag(name = "Resident Controller", description = "APIs to manage residents and their vehicles")
//Tag for swagger api documentation
public class ResidentController
{
    @Autowired
    private ResidentService residentService;

    //API to create Resident with vehicals
    @PostMapping("/addResident")
    @Operation(summary = "Create a new resident with vehicles")
    public ResponseEntity<String> createResident(@Valid @RequestBody Resident resident){
        String msg = residentService.saveResident(resident);
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }

    //API to get all resident along with their vehicle
    @GetMapping("/getAllResident")
    @Operation(summary = "Get all resident details")
    public ResponseEntity<List<GetResidentDataRequest>> getAllResidents() {
        List<Resident> residents = residentService.getAllResidents();

        List<GetResidentDataRequest> dtoList = residents.stream()
                .map(ResidentMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    //API to get resident details with name
    // ? -> means it can either return name or string msg
    @GetMapping("/getResidentByName")
    @Operation(summary = "Get Resident details with name")
    public ResponseEntity<?> getResidentByName(
            @Pattern(regexp = "^[A-Za-z]*$", message = "First name should not contain numbers")
            @Parameter(description = "First name of resident (optional)")
            @RequestParam(required = false) String fName,

            @Pattern(regexp = "^[A-Za-z]*$", message = "Last name should not contain numbers")
            @Parameter(description = "Last name of resident (optional)")
            @RequestParam(required = false) String lName) {

        List<Resident> residentList = residentService.getResidentByName(fName, lName);

        if (residentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Resident not found with given name inputs.");
        }

        List<GetResidentDataRequest> dtoList = residentList.stream()
                .map(ResidentMapperWithVehicle::toGetResident)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }
}
