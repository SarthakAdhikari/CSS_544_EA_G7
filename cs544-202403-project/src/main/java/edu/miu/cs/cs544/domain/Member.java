package edu.miu.cs.cs544.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "barcode")
	private String barcode;

    @Column(name = "email")
    private String email;

    //One-Many members can have One-Many roles
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Role> roles;

    //One member can have One-Many accounts
    @ManyToMany (cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Account> accounts;

    //One-Many members can have One-Many events
    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Event> events;

    public Member() {}

    public Member(String firstName, String lastName, String email, String barcode, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.barcode = barcode;
        this.roles = roles;
    }
}
