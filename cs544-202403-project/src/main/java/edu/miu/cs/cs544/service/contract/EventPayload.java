package edu.miu.cs.cs544.service.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Schedule;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class EventPayload implements Serializable {

    private static final long serialVersionUID = 1L; // Recommended for Serializable classes

    private String name;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private AccountType accountType;

    private List<Schedule> schedules;

    private Set<Member> members;

}

