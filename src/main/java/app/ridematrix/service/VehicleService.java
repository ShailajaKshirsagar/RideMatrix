package app.ridematrix.service;

import app.ridematrix.dto.CreateVehicleRequest;
import app.ridematrix.dto.GetResidentDataRequest;

public interface VehicleService
{
    String saveVehicle(CreateVehicleRequest vehicle);

    GetResidentDataRequest getResidentByRegNum(String registrationNum);
}
