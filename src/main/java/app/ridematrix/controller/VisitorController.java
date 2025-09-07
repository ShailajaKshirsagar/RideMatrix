package app.ridematrix.controller;

import app.ridematrix.dto.VisitorRequestDto;
import app.ridematrix.dto.VisitorResponseDTO;
import app.ridematrix.entity.Visitors;
import app.ridematrix.service.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
@Slf4j
@Tag(name = "Visitor Controller", description = "APIs to manage visitors and their vehicles")
public class VisitorController
{
    //inject service into this
    @Autowired
    private VisitorService visitorService;

    //API To post visitor
    @PostMapping("/addVisitor")
    @Operation(summary = "Add visitor ")
    public ResponseEntity<String> addVisitor(@Valid @RequestBody VisitorRequestDto visitorRequestDto ){
        log.info("POST /visitors/addVisitor - New visitor request received: {}", visitorRequestDto);
        String msg = visitorService.saveVisitor(visitorRequestDto);
        log.info("Visitor saved successfully: {}", msg);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    //Get visitors details with resident details by registeration number.
    @GetMapping("/getVisitorandResidentDetails/{vehicleRegNum}")
    @Operation(summary = "Get resident and visitor details by Vehicle Number")
    public ResponseEntity<VisitorResponseDTO> getVisitorAndResidentDetByVehNum(
            @Pattern(
                    regexp = "^[A-Z0-9]{4}-[A-Z]{2}-\\d{4}$",
                    message = "Registration number must be in format XX-00-XX-0000 equal to 10"
            )
            @Valid @PathVariable String vehicleRegNum) {
        log.info("GET /visitors/getVisitorandResidentDetails/{} - Fetching visitor and resident details", vehicleRegNum);
        VisitorResponseDTO responseDTO = visitorService.findVisitorResidentByRegNum(vehicleRegNum);
        log.info("Response fetched for vehicle {}: {}", vehicleRegNum, responseDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    //API to update the end time of visitor by vehicle number using patch
    @PatchMapping("/updateOutTime/{vehicleRegNum}")
    @Operation(summary = "Update end time of visitor")
    public ResponseEntity<String> updateTimeOut(@PathVariable String vehicleRegNum) {
        log.info("PATCH /visitors/updateOutTime/{} - Updating visitor exit time", vehicleRegNum);

        String result = visitorService.updateOutTime(vehicleRegNum);

        if (result != null) {
            log.info("Visitor exit time updated successfully for {}", vehicleRegNum);
            return ResponseEntity.ok(result);
        } else {
            log.warn("Visitor not found with vehicle number: {}", vehicleRegNum);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visitor not found with this vehicle number");
        }
    }

    //API to get all active users in society
    @GetMapping("/getActiveVisitorsInSociety")
    @Operation(summary = "Get active visitor Details")
    public ResponseEntity<List<VisitorResponseDTO>> getAllActiveVisitors(
            @Parameter(description = " GUEST or DELIVERY or BOTH")
            @RequestParam(required = false) List<Visitors.VisitorType> visitorType){
        //created converter class to convert string into enum or map to enum
        log.info("GET /visitors/getActiveVisitorsInSociety - Fetching active visitors with filter: {}", visitorType);
        List<VisitorResponseDTO> activeVisitorList = visitorService.findVisitorType(visitorType);
        log.info("Found {} active visitors", activeVisitorList.size());
        return new ResponseEntity<>(activeVisitorList,HttpStatus.OK);
    }
}
