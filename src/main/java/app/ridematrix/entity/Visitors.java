package app.ridematrix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Visitors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @NotBlank(message = "Visitor name is required")
    private String visitorName;
    private String vehicleName;

    @NotBlank(message = "Registration Number is Required")
    @Pattern(
            regexp = "^[A-Z0-9]{4}-[A-Z]{2}-\\d{4}$",
            message = "Registration number must be in format XX-00-XX-0000 equal to 10"
    )
    private String vehicleRegNumber;
    private String visitPurpose;

    //it will be automatically calculated
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime timeIn;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime timeOut;

    @NotNull(message = "Phone number is required")
    private long phoneNum;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isActiveVisitor;

    //set by default value true of isactivisitor while saving it
    @PrePersist
    protected void onCreate() {
        this.isActiveVisitor = true;
    }

    @Enumerated(EnumType.STRING)
    private VisitorType visitorType;

    public enum VisitorType {
        GUEST,
        DELIVERY
    }

    //many to one mapped with resident
    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;

    //visit duration
    private String visitDuration;

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
        calculateVisitDuration();
    }

    //calculatevisitduration-> because here we want output as HH MM
    public String calculateVisitDuration(){
        if(timeIn!= null && timeOut!=null){
            Duration duration = Duration.between(timeIn,timeOut);
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            String durationString = String.format("%dH:%dH", hours, minutes);
            this.visitDuration = durationString;
            return durationString;
        }
        return null;
    }
}
