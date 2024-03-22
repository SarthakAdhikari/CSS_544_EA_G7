package edu.miu.cs.cs544.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    private  AccountType accountType;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Schedule> schedules;

    @ManyToMany(mappedBy = "events")
    private Set<Member> members;

    public Event() {}

    public Event(String name, String description, LocalDateTime startDateTime, LocalDateTime endDateTime, List<Schedule> schedules, Set<Member> members, AccountType accountType) {
        this.name = name;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.schedules = schedules;
        this.members = members;
        this.accountType = accountType;
    }
    @Override
    public String toString() {
        return new StringJoiner(", ", "", "")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("startDateTime=" + startDateTime)
                .add("endDateTime=" + endDateTime)
                .toString();
    }

}
