package app.ridematrix.controller;

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
    @GetMapping("/getAllResidents")
    @Operation(summary = "Get all Residents details with Vehicles")
    public ResponseEntity<List<Resident>> getAllResident(){
        List<Resident> residentList = residentService.getAllResidents();
        return new ResponseEntity<>(residentList,HttpStatus.OK);
    }

    //API to get resident details with name
    // ? -> means it can either return name or string msg
    @GetMapping("/getResidentByName")
    @Operation(summary = "Get Resident details with name")
    public ResponseEntity<?> getResidentByName(
            @Pattern(regexp = "^[A-Za-z]*$", message = "First name should not contain numbers")
            @Parameter(description = "First name of resident (optional)")
            @RequestParam(required = false) String fName ,
            @Pattern(regexp = "^[A-Za-z]*$", message = "Last name should not contain numbers")
            @Parameter(description = "Last name of resident (optional)")
            @RequestParam(required = false) String lName)
    {
        List<Resident> residentByName = residentService.getResidentByName(fName, lName);
        if (residentByName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not found with given name inputs.");
        }
        return ResponseEntity.ok(residentByName);
    }
}
