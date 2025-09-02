package app.ridematrix.dto;

import app.ridematrix.entity.Visitors;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class VisitorRequestDto
{

        private String visitorName;
        private String vehicleName;
        private String vehicleRegNumber;
        private String visitPurpose;
        private long phoneNum;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private LocalDateTime timeIn;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private LocalDateTime timeOut;
        private boolean activeVisitor;
        private Visitors.VisitorType visitorType;
        private String flatNo;
}
