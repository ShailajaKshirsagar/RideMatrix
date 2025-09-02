package app.ridematrix.service;

import app.ridematrix.dto.VisitorRequestDto;
import jakarta.validation.Valid;

public interface VisitorService
{
    String saveVisitor(@Valid VisitorRequestDto visitors);
}
