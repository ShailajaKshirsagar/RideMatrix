package app.ridematrix.service;

import app.ridematrix.entity.Resident;

import java.util.List;

public interface ResidentService
{
    String saveResident(Resident resident);

    List<Resident> getAllResidents();
}
