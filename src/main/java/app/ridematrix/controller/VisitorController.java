package app.ridematrix.controller;

import app.ridematrix.dto.VisitorRequestDto;
import app.ridematrix.entity.Visitors;
import app.ridematrix.service.VisitorService;
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
@RequestMapping("/visitors")
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
        String msg = visitorService.saveVisitor(visitorRequestDto);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
