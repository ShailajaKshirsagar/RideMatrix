package app.ridematrix.entity;

import jakarta.persistence.*;
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
    @SequenceGenerator(
            name = "vehicle_seq",
            sequenceName = "vehicle_sequence",
            allocationSize = 1,
            initialValue = 10000 // starting value for Vehicle IDs
    )
    private long id;
    private String registrationNum;
    private String vColor;

    @Enumerated(EnumType.STRING)
    private VehicleType vType;

    public enum VehicleType{
        CAR,
        MOPED,
        BIKE
    }

    private LocalDateTime associationActivatedAt;
    private LocalDateTime associationDeactivatedAt;

    private boolean isVehicleActive;

    // Many-to-one mapping with Resident
    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

}
