package edu.miu.cs.cs544.domain;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Scanner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @OneToOne
    private Event event;



    @OneToMany(mappedBy = "scanner")
    private List<Record> records;

    public Scanner() {}

    public Scanner(Location location, Event event) {
        this.location = location;
        this.event = event;
    }

    public Scanner(Long id, Location location, Event event) {
        this.id = id;
        this.location = location;
        this.event = event;
    }
}
