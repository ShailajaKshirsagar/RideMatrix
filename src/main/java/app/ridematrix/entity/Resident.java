package app.ridematrix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//Resident entity
@Entity
@Setter
@Getter
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private String fName;

    private String lName;

    private String flatNo;

    private long mobNo;

    private String email;

    @Enumerated(EnumType.STRING)
    private ResidentType residentType;

    // Enum definition
    public enum ResidentType {
        OWNER,
        TENANT
    }
    //one to many mapping
    @OneToMany(mappedBy = "resident", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicleList = new ArrayList<>();
}
