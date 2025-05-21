package com.Lab2;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    public Ticket() {}

    public Ticket(String seatNumber, double price, Session session) {
        this.seatNumber = seatNumber;
        this.price = price;
        this.session = session;
    }

    // Геттери та сеттери
    public Long getId() { return id; }

    public String getSeatNumber() { return seatNumber; }

    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public Session getSession() { return session; }

    public void setSession(Session session) { this.session = session; }

    public void setId(Long id) {
        this.id = id;
    }
}
