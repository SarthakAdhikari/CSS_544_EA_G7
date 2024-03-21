package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Schedule;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EventPayload implements Serializable {

  private static final long serialVersionUID = 1L;



    private String name;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


    private List<Schedule> schedules;


    private Scanner scanner;


    private Set<Member> members;


}
