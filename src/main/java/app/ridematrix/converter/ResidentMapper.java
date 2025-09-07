package app.ridematrix.converter;

import app.ridematrix.dto.GetResidentDataRequest;
import app.ridematrix.entity.Resident;

public class ResidentMapper {

    public static GetResidentDataRequest toDto(Resident resident) {

        return GetResidentDataRequest.builder()
                .id(resident.getId())
                .fName(resident.getFName())
                .lName(resident.getLName())
                .flatNo(resident.getFlatNo())
                .mobNo(resident.getMobNo())
                .email(resident.getEmail())
                .residentType(resident.getResidentType())
                .vehicleList(resident.getVehicleList())
                .build();
    }
}
