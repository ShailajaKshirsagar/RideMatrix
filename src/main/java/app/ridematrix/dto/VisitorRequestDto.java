package app.ridematrix.dto;

import app.ridematrix.entity.Visitors;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VisitorRequestDto
{

        @NotNull(message = "Visitor name is required")
        private String visitorName;
        private String vehicleName;
        @NotNull(message = "Registration Number is Required")
         @Pattern(
            regexp = "^[A-Z0-9]{4}-[A-Z]{2}-\\d{4}$",
            message = "Registration number must be in format XX-00-XX-0000 equal to 10"
        )
        private String vehicleRegNumber;
        private String visitPurpose;
        @NotNull(message = "Phone number is required")
        private long phoneNum;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private LocalDateTime timeIn;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private LocalDateTime timeOut;
        private boolean activeVisitor;
        private Visitors.VisitorType visitorType;
        private String flatNo;
}
