package app.ridematrix.service;

import app.ridematrix.dto.CreateVehicleRequest;

public interface VehicleService
{
    String saveVehicle(CreateVehicleRequest vehicle);
}
