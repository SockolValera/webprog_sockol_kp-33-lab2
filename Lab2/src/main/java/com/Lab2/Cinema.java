package com.Lab2;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL)
    private List<Session> sessions;

    public Cinema() {}

    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Геттери та сеттери
    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public List<Session> getSessions() { return sessions; }

    public void setSessions(List<Session> sessions) { this.sessions = sessions; }
}
