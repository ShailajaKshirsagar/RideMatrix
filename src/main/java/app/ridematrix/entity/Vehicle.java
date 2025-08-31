package app.ridematrix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//vehicle
@Entity
@Setter
@Getter
public class Vehicle
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @SequenceGenerator(
            name = "vehicle_seq",
            sequenceName = "vehicle_sequence",
            allocationSize = 1,
            initialValue = 10000 // starting value for Vehicle IDs
    )
    private long id;
    @NotNull(message = "Registration Number is Required")
    private String registrationNum;
    private String vColor;

    @NotNull(message = "Vehicle Type is Required")
    @Enumerated(EnumType.STRING)
    private VehicleType vType;

    public enum VehicleType{
        CAR,
        MOPED,
        BIKE
    }

    @JsonIgnore
    private LocalDateTime associationActivatedAt;
    @JsonIgnore
    private LocalDateTime associationDeactivatedAt;

    private boolean isVehicleActive;

    // Many-to-one mapping with Resident
    @ManyToOne
    @JoinColumn(name = "resident_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Resident resident;

}
