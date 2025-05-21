package com.Lab2;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public Session() {}

    public Session(String movieTitle, LocalDateTime startTime, Cinema cinema) {
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.cinema = cinema;
    }

    // Геттери та сеттери
    public Long getId() { return id; }

    public String getMovieTitle() { return movieTitle; }

    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public LocalDateTime getStartTime() { return startTime; }

    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public Cinema getCinema() { return cinema; }

    public void setCinema(Cinema cinema) { this.cinema = cinema; }

    public List<Ticket> getTickets() { return tickets; }

    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
}
