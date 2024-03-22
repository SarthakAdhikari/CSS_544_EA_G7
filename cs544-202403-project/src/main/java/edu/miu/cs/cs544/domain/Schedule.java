package edu.miu.cs.cs544.domain;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<ScheduleDayOfWeek> scheduleDayOfWeeks;
    private String scheduleName;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, orphanRemoval = true)
    @JsonIgnore
    private Set<Session> sessions;

      @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
      @JoinColumn
      @JsonIgnore
    private Event event;

    public Schedule() {}

    public Schedule(LocalTime startTime, LocalTime endTime, Set<ScheduleDayOfWeek> scheduleDayOfWeeks, String scheduleName, Set<Session> sessions, Event event) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleDayOfWeeks = scheduleDayOfWeeks;
        this.scheduleName = scheduleName;
        this.sessions = sessions;
        this.event = event;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", scheduleName='" + scheduleName + '\'' +
                '}';
    }

}
