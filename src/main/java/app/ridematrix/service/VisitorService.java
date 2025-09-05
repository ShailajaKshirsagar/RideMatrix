package app.ridematrix.service;

import app.ridematrix.dto.VisitorRequestDto;
import app.ridematrix.dto.VisitorResponseDTO;
import app.ridematrix.entity.Visitors;
import jakarta.validation.Valid;

import java.util.List;

public interface VisitorService
{
    String saveVisitor(@Valid VisitorRequestDto visitors);

    VisitorResponseDTO findVisitorResidentByRegNum(String vehicleRegNum);

    //update out time by vehicle num
    String updateOutTime(String vehicleRegNum);

    // type of visitor
    List<VisitorResponseDTO> findVisitorType(List<Visitors.VisitorType> visitorType);
}
