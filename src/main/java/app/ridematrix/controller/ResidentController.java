package app.ridematrix.controller;

import app.ridematrix.entity.Resident;
import app.ridematrix.service.ResidentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Resident")
public class ResidentController
{
    @Autowired
    private ResidentService residentService;

    //API to create Resident with vehicals
    @PostMapping("/addResident")
    public ResponseEntity<String> createResident(@Valid @RequestBody Resident resident){
        String msg = residentService.saveResident(resident);
        return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }
}
