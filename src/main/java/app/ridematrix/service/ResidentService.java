package app.ridematrix.service;

import app.ridematrix.entity.Resident;
import app.ridematrix.entity.Visitors;

import java.util.List;

public interface ResidentService
{
    String saveResident(Resident resident);

    List<Resident> getAllResidents();

    List<Resident> getResidentByName(String fName,String lName);


}
