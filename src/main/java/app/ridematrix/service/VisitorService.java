package app.ridematrix.service;

import app.ridematrix.dto.VisitorRequestDto;
import app.ridematrix.dto.VisitorResponseDTO;
import jakarta.validation.Valid;

public interface VisitorService
{
    String saveVisitor(@Valid VisitorRequestDto visitors);

    VisitorResponseDTO findVisitorResidentByRegNum(String vehicleRegNum);

    //update out time by vehicle num
    String updateOutTime(String vehicleRegNum);
}
