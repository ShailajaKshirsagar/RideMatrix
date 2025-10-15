package app.ridematrix.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Registration Number is Required")
    @Pattern(
            regexp = "^[A-Z0-9]{4}-[A-Z]{2}-\\d{4}$",
            message = "Registration number must be in format XX-00-XX-0000 equal to 10"
    )
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
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    private Resident resident;
}
